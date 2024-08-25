package com.example.testapplication.domain.repository

interface AuthRepository {

    suspend fun sendPhone(phone: String): Boolean
    suspend fun sendPhoneAndCode(phone: String, code: String): Boolean
    suspend fun sendUsername(phone : String,name : String, username : String) : Boolean

}