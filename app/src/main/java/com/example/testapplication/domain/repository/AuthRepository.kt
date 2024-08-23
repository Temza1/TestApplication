package com.example.testapplication.domain.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepository {

    suspend fun sendPhone(phone: String): Boolean
    suspend fun sendPhoneAndCode(phone: String, code: String): Boolean
    suspend fun checkJWT(): String

}