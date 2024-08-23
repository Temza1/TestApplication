package com.example.testapplication.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.presentation.ChatListViewModel
import com.example.testapplication.presentation.login.AuthCodeCheckScreen
import com.example.testapplication.presentation.login.AuthPhoneScreen
import com.example.testapplication.ui.theme.TestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val vm: ChatListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "AuthPhoneScreen") {
                composable("AuthPhoneScreen") {
                    AuthPhoneScreen(
                        viewModel = vm,
                        startAuthCodeCheckScreen = { navController.navigate("AuthCodeCheckScreen") }
                    )
                }
                composable("AuthCodeCheckScreen") {
                    AuthCodeCheckScreen()
                }
                composable("RegScreen") {}
                composable("ChatListScreen") {}
                composable("ProfileScreen") {}
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    TestApplicationTheme {
    }
}