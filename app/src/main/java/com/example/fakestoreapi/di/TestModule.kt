package com.example.fakestoreapi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class TestModule {

    @Provides
    @Named("first_string")
    fun provideString(): String {
        return "string injected is here: batata for sale"
    }
    @Provides
    @Named("second_string")
    fun provideAnotherString(): String {
        return "other string"
    }

}