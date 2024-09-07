package com.example.testapplication.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapplication.navigation.DETAIL_ARGUMENT_KEY
import com.example.testapplication.navigation.Screen
import com.example.testapplication.navigation.TestApplication
import com.example.testapplication.navigation.rememberTestApplicationState
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreenViewModel
import com.example.testapplication.presentation.registrationScreen.RegistrationScreen
import com.example.testapplication.presentation.authCodeCheckScreen.AuthCodeCheckScreen
import com.example.testapplication.presentation.authCodeCheckScreen.AuthCodeCheckScreenViewModel
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreen
import com.example.testapplication.presentation.registrationScreen.RegScreenViewModel
import com.example.testapplication.ui.theme.TestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    val LOG_MAIN_ACTIVITY = "MainActivity"

    private val authPhoneScreenVM: AuthPhoneScreenViewModel by viewModels()
    private val authCodeCheckScreenVM: AuthCodeCheckScreenViewModel by viewModels()
    private val regScreenVM: RegScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberTestApplicationState()

            TestApplication(appState)

//            val navController = rememberNavController()
//
//            NavHost(
//                navController = navController,
//                startDestination = Screen.AuthPhoneScreen.route
//            ) {
//                try {
//                    composable(route = Screen.AuthPhoneScreen.route) {
//                        AuthPhoneScreen(
//                            viewModel = authPhoneScreenVM,
//                            navController = navController
//                        )
//                    }
//                } catch (e : IllegalStateException) {
//                    Log.d(LOG_MAIN_ACTIVITY,e.toString())
//                }
//                composable(
//                    route = Screen.AuthCodeCheckScreen.route,
//                    arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
//                        type = NavType.StringType
//                        nullable = true
//                    })
//                ) {
//                    val phone = it.arguments?.getString(DETAIL_ARGUMENT_KEY).default("null")
//                    AuthCodeCheckScreen(
//                        navController = navController,
//                        viewModel = authCodeCheckScreenVM,
//                        phone = phone
//                    )
//                }
//                composable(
//                    Screen.RegistrationScreen.route,
//                    arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
//                        type = NavType.StringType
//                        nullable = true
//                    })
//                ) {
//                    val phone = it.arguments?.getString(DETAIL_ARGUMENT_KEY).default("null")
//                    RegistrationScreen(
//                        viewModel = regScreenVM,
//                        phone = phone,
//                        navController = navController
//                    )
//                }
//                composable("chat_list_screen") {}
//                composable("profile_screen") {}
//            }
//        }
    }
}

fun <T> T?.default(default: T): T {
    return this ?: default
}



@Preview(showBackground = true)
@Composable
fun Preview() {
    TestApplicationTheme {
    }
}}