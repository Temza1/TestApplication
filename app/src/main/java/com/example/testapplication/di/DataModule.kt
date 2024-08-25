package com.example.testapplication.di

import android.content.Context
import com.example.testapplication.data.api.AuthApiService
import com.example.testapplication.data.repository.AuthRepositoryImpl
import com.example.testapplication.data.mapper.AuthMapper
import com.example.testapplication.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import utils.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun bindsAuthRepositoryImpl(@ApplicationContext context: Context,authApiService: AuthApiService,mapper: AuthMapper): AuthRepositoryImpl {
        return AuthRepositoryImpl(context,authApiService,mapper)
    }

    @Provides
    fun provideAuthApiService(
        retrofit: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ) : AuthApiService {
        return retrofit.baseUrl(BASE_URL)
            .client(okHttpClient.newBuilder().build())
            .build()
            .create(AuthApiService::class.java)
    }
}