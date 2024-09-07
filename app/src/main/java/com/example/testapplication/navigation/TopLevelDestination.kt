package com.example.testapplication.navigation

import com.example.testapplication.R

enum class TopLevelDestination(
    val titleTextId: Int
) {
    AUTH(R.string.feature_auth),
    CODECHECK(R.string.feature_code_check),
    REGISTER(R.string.feature_register),
    CHAT(R.string.feature_chat),
    PROFILE(R.string.feature_profile),
}