package com.example.testapplication.presentation.profileScreen

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



fun NavController.navigateToProfileScreen(navOptions: NavOptions) = navigate(Screen.ProfileScreen.route, navOptions)

fun NavGraphBuilder.profileScreen(
    onClickReturn: () -> Unit
) {
    composable(
        route = Screen.ProfileScreen.route
        ) {
        ProfileScreen(onClickReturnButton = onClickReturn)
    }
}