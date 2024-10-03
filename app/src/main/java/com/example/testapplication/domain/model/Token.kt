package com.example.testapplication.domain.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val userId: Int,
    val isUserExist: Boolean
)
