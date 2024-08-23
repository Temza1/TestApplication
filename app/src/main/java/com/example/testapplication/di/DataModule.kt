package com.example.testapplication.di

import com.example.testapplication.data.api.AuthApiService
import com.example.testapplication.data.repository.AuthRepositoryImpl
import com.example.testapplication.data.mapper.AuthMapper
import com.example.testapplication.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindsAuthRepositoryImpl(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsApiServiceImpl(impl: AuthApiService): AuthApiService

}