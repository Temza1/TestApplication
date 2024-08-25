package com.example.testapplication.data.model

import com.google.gson.annotations.SerializedName

data class UsernameDto(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("refresh_token")
    val refreshToken: String?,
    @SerializedName("user_id")
    val userId: Int?
)