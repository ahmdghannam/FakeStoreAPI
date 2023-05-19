package com.example.fakestoreapi.model.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fakestoreapi.model.local.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

// this is a table of just one tuple, but this is for training
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTheUser(user: UserEntity): Completable

    @Query("select * from TABLE_USER")
    fun getTheUser(): Single<UserEntity>
}