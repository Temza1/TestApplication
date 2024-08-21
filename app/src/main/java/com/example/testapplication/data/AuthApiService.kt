package com.example.testapplication.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {

    @POST("/send-auth-code")
    suspend fun sendAuthCode(phone: String): Response<AuthPhoneDto>

    @POST("/check-auth-code")
    suspend fun checkAuthCode(phone: String, code: String): Response<TokenDto>

    @GET("/check-jwt")
    suspend fun checkJWT(@Header("Authorization") authToken: String): Response<String>



}