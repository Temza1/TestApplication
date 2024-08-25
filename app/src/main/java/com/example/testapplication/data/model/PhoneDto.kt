package com.example.testapplication.data.model

import com.google.gson.annotations.SerializedName

data class PhoneDto(
    @SerializedName("is_success")
    val isSuccess: Boolean
)