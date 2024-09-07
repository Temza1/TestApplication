package com.example.testapplication.presentation.authCodeCheckScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testapplication.navigation.DETAIL_ARGUMENT_KEY
import com.example.testapplication.navigation.Screen
import com.example.testapplication.presentation.registrationScreen.navigation.default


fun NavController.navigateToCodeCheck(phone : String,navOptions: NavOptions) =
    navigate(Screen.AuthCodeCheckScreen.passPhone(phone), navOptions)

fun NavGraphBuilder.authCodeCheckScreen(
    onAuthCodeCheckFail: (String) -> Unit,
    onAuthCodeCheckSuccess: () -> Unit
) {
    composable(
        route = Screen.AuthCodeCheckScreen.route,
        arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
            type = NavType.StringType
            nullable = true
        })
    ) {
        val phone = it.arguments?.getString(DETAIL_ARGUMENT_KEY).default("null")
        AuthCodeCheckScreen(onAuthCodeCheckFail, onAuthCodeCheckSuccess,phone = phone)
    }
}

fun <T> T?.default(default: T): T {
    return this ?: default
}