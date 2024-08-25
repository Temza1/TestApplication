package com.example.testapplication.data.api

import com.example.testapplication.data.model.PhoneDto
import com.example.testapplication.data.model.CodeRequest
import com.example.testapplication.data.model.PhoneRequest
import com.example.testapplication.data.model.TokenDto
import com.example.testapplication.data.model.UsernameDto
import com.example.testapplication.data.model.UsernameRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(@Body request: PhoneRequest): Response<PhoneDto>

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(@Body request: CodeRequest): Response<TokenDto>

    @POST("api/v1/users/register/")
    suspend fun register(@Body request: UsernameRequest): Response<UsernameDto>

}