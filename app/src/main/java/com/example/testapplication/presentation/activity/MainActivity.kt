package com.example.testapplication.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphNavigator
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapplication.navigation.DETAIL_ARGUMENT_KEY
import com.example.testapplication.navigation.Screen
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreenViewModel
import com.example.testapplication.presentation.registrationScreen.RegistrationScreen
import com.example.testapplication.presentation.authCodeCheckScreen.AuthCodeCheckScreen
import com.example.testapplication.presentation.authCodeCheckScreen.AuthCodeCheckScreenViewModel
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreen
import com.example.testapplication.presentation.registrationScreen.RegScreenViewModel
import com.example.testapplication.ui.theme.TestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.AuthPhoneScreen.route
                ) {
                    composable(route = Screen.AuthPhoneScreen.route) {
                        val viewModel = hiltViewModel<AuthPhoneScreenViewModel>()
                        val state by viewModel.state.collectAsState()
                        AuthPhoneScreen(
                            startCodeCheckScreen = {navController.navigate(Screen.AuthCodeCheckScreen.passPhone(it))},
                            state = state,
                            onEvent = viewModel::sendEvent
                        )

                    }
                    composable(
                        route = Screen.AuthCodeCheckScreen.route,
                        arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
                            type = NavType.StringType
                            nullable = true
                        })
                    ) {
                        val viewModel = hiltViewModel<AuthCodeCheckScreenViewModel>()
                        val state by viewModel.state.collectAsState()
                        val phone = it.arguments?.getString(DETAIL_ARGUMENT_KEY).default("null")
                        AuthCodeCheckScreen(
                            startRegScreen = {navController.navigate(Screen.RegistrationScreen.passPhone(phone))},
                            startChatListScreen = {},
                            state = state,
                            onEvent = viewModel::sendEvent,
                            phone = phone
                        )
                    }
                    composable(
                        Screen.RegistrationScreen.route,
                        arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
                            type = NavType.StringType
                            nullable = true
                        })
                    ) {
                        val viewModel = hiltViewModel<RegScreenViewModel>()
                        val state by viewModel.state.collectAsState()
                        val phone = it.arguments?.getString(DETAIL_ARGUMENT_KEY).default("null")
                        RegistrationScreen(
                            state = state,
                            onEvent = viewModel::sendEvent,
                            startAuthPhoneScreen = {navController.navigate(route = Screen.AuthPhoneScreen.route)},
                            phone = phone
                        )
                    }
                    composable("chat_list_screen") {}
                    composable("profile_screen") {}
                }
            }
        }
    }
}

fun <T> T?.default(default: T): T {
    return this ?: default
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TestApplicationTheme {
    }
}