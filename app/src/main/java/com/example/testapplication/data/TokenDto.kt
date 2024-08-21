package com.example.testapplication.data

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("access_token")
    val successToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("is_user_exists")
    val isUserExist: Boolean
)
