package com.example.testapplication.presentation.registrationScreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testapplication.navigation.DETAIL_ARGUMENT_KEY
import com.example.testapplication.navigation.Screen
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreen
import com.example.testapplication.presentation.registrationScreen.RegistrationScreen

fun NavController.navigateToReg(phone: String, navOptions: NavOptions) = navigate(Screen.RegistrationScreen.passPhone(phone), navOptions)

fun NavGraphBuilder.registrationScreen(
    onRegSuccess: () -> Unit
) {
    composable(
        route = Screen.RegistrationScreen.route,
        arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
            type = NavType.StringType
            nullable = true
        })
    ) {
        val phone = it.arguments?.getString(DETAIL_ARGUMENT_KEY).default("null")
        RegistrationScreen(onRegSuccess = onRegSuccess,phone = phone)
    }
}

fun <T> T?.default(default: T): T {
    return this ?: default
}