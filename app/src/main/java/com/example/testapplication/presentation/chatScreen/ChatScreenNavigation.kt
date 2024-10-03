package com.example.testapplication.presentation.chatScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.testapplication.navigation.Screen

fun NavController.navigateToChatList(navOptions: NavOptions) = navigate(Screen.ChatScreen.route, navOptions)

fun NavGraphBuilder.chatListScreen(
    onClickReturnButton: () -> Unit
) {
    composable(
        route = Screen.ChatListScreen.route
    ) {
        ChatScreen(onClickReturnButton = onClickReturnButton)
    }
}