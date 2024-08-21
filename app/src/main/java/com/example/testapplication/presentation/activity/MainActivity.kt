package com.example.testapplication.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.presentation.MainScreen
import com.example.testapplication.presentation.login.AuthCodeCheckScreen
import com.example.testapplication.presentation.login.AuthPhoneScreen
import com.example.testapplication.ui.theme.TestApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "AuthPhoneScreen") {
                composable("AuthPhoneScreen") {
                    AuthPhoneScreen(
                        startAuthCodeCheckScreen = {navController.navigate("AuthCodeCheckScreen")}
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