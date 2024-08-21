package com.example.testapplication.data

import com.google.gson.annotations.SerializedName

data class AuthPhoneDto (
    @SerializedName("is_success")
    val isSuccess: Boolean
)