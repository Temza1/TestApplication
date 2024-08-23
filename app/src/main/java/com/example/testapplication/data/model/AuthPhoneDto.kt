package com.example.testapplication.data.model

import com.google.gson.annotations.SerializedName

data class AuthPhoneDto(
    @SerializedName("is_success")
    val isSuccess: Boolean
)