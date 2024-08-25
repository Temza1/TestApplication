package com.example.testapplication.di

import com.example.testapplication.data.mapper.AuthMapper
import com.example.testapplication.data.repository.AuthRepositoryImpl
import com.example.testapplication.domain.repository.AuthRepository
import com.example.testapplication.domain.useCases.SendCodeUseCase
import com.example.testapplication.domain.useCases.SendPhoneUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideSendPhone(authRepository: AuthRepositoryImpl): SendPhoneUseCase {
        return SendPhoneUseCase(authRepository)
    }

    @Provides
    fun provideCheckCode(authRepository: AuthRepositoryImpl): SendCodeUseCase {
        return SendCodeUseCase(authRepository)
    }

    @Provides
    fun provideMapper(): AuthMapper {
        return AuthMapper()
    }
}