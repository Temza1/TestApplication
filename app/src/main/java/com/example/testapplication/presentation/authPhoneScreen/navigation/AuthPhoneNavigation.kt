package com.example.testapplication.presentation.authPhoneScreen.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testapplication.navigation.DETAIL_ARGUMENT_KEY
import com.example.testapplication.navigation.Screen
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreen



fun NavController.navigateToAuthPhone(navOptions: NavOptions) = navigate(Screen.AuthPhoneScreen.route, navOptions)

fun NavGraphBuilder.authPhoneScreen(
    onAuthPhoneSuccess: (String) -> Unit
) {
    composable(
        route = Screen.AuthPhoneScreen.route
        ) {
        AuthPhoneScreen(onAuthPhoneSuccess)
    }
}