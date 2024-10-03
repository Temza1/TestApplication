package com.example.testapplication.domain.repository

import com.example.testapplication.data.handleErrorNetwork.ApiResult
import com.example.testapplication.domain.model.AuthResult
import com.example.testapplication.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun sendPhone(phone: String): Boolean
    suspend fun sendPhoneAndCode(phone: String, code: String): Flow<ApiResult<*>>
    suspend fun sendUsername(phone : String,name : String, username : String) : Flow<ApiResult<*>>

}