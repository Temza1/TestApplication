package com.example.testapplication.domain

import com.google.gson.annotations.SerializedName

data class Token(
    val successToken: String,
    val refreshToken: String,
    val userId: Int,
    val isUserExist: Boolean
)
