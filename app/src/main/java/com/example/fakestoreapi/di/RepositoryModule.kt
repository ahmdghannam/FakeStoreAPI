package com.example.fakestoreapi.di

import android.content.Context
import com.example.fakestoreapi.model.api.FakeStoreApiService
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.model.local.StoreDataBase
import com.example.fakestoreapi.model.local.daos.UserDao
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(
        sharedPreferencesUtil: SharedPreferencesUtil,
        apiService: FakeStoreApiService,
        userDao: UserDao
    ): Repository {
        return RepositoryImpl(sharedPreferencesUtil, apiService, userDao)
    }

    @Provides
    fun provideUserDao(): UserDao {
        return StoreDataBase.getInstance().userDao()
    }

    @Provides
    fun provideSharedPreferencesUtil(@ApplicationContext context: Context): SharedPreferencesUtil {
        return SharedPreferencesUtil(context)
    }
}