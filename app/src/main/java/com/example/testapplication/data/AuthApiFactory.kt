package com.example.testapplication.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthApiFactory {
    companion object {

        private const val BASE_URL = "https://plannerok.ru/api/v1/users"

        fun create() : AuthApiService {

            val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(AuthApiService::class.java)
        }

    }
}