package com.example.testapplication.presentation.chatListScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.testapplication.navigation.Screen

fun NavController.navigateToChatList(navOptions: NavOptions) = navigate(Screen.ChatListScreen.route, navOptions)

fun NavGraphBuilder.chatListScreen(
    onClickProfileButton: () -> Unit
) {
    composable(
        route = Screen.ChatListScreen.route
    ) {
        ChatListScreen(onClickProfileButton = onClickProfileButton)
    }
}