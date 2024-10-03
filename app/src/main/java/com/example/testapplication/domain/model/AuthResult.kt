package com.example.testapplication.domain.model

data class AuthResult(
    val data : Token,
    val error : String,
    val loading : Boolean
)
