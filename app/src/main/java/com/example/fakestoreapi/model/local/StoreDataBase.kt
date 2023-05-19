package com.example.fakestoreapi.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fakestoreapi.model.local.daos.UserDao
import com.example.fakestoreapi.model.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class StoreDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "UserDatabase"
        private var instance: StoreDataBase? = null
        fun init(context: Context): StoreDataBase {
            return instance ?: synchronized(this) {
                buildDataBase(context).also {
                    instance = it
                }
            }
        }

        fun getInstance() = instance!!

        private fun buildDataBase(context: Context): StoreDataBase {
            return Room.databaseBuilder(context, StoreDataBase::class.java, DATABASE_NAME).build()
        }
    }
}