package com.example.testapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.example.testapplication.presentation.chatListScreen.chatListScreen
import com.example.testapplication.presentation.chatListScreen.navigateToChatList
import com.example.testapplication.presentation.authCodeCheckScreen.authCodeCheckScreen
import com.example.testapplication.presentation.authCodeCheckScreen.navigateToCodeCheck
import com.example.testapplication.presentation.authPhoneScreen.navigation.authPhoneScreen
import com.example.testapplication.presentation.authPhoneScreen.navigation.navigateToAuthPhone
import com.example.testapplication.presentation.registrationScreen.navigation.navigateToReg
import com.example.testapplication.presentation.registrationScreen.navigation.registrationScreen

@Composable
fun ChatNavHost(
    appState: TestApplicationState,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.AuthPhoneScreen.route,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        authPhoneScreen(
            onAuthPhoneSuccess = {
                (navController::navigateToCodeCheck)(it, navOptions {})
            }
        )
        authCodeCheckScreen(
            onAuthCodeCheckFail = {(navController::navigateToReg)(it, navOptions { })},
            onAuthCodeCheckSuccess = {(navController::navigateToChatList)(navOptions { })}
        )

        registrationScreen(
            onRegSuccess = {(navController::navigateToAuthPhone)(navOptions { })}
        )

        chatListScreen(
            onClickProfileButton = {}
        )
    }
}