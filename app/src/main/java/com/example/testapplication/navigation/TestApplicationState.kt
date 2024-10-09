package com.example.testapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.testapplication.presentation.chatListScreen.navigateToChatList
import com.example.testapplication.presentation.authCodeCheckScreen.navigateToCodeCheck
import com.example.testapplication.presentation.authPhoneScreen.navigation.navigateToAuthPhone
import com.example.testapplication.presentation.profileScreen.navigateToProfileScreen
import com.example.testapplication.presentation.registrationScreen.navigation.navigateToReg
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberTestApplicationState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): TestApplicationState {
    return remember(
        navController, coroutineScope
    ) {
        TestApplicationState(navController, coroutineScope)
    }
}

class TestApplicationState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            Screen.AuthPhoneScreen.route -> TopLevelDestination.AUTH
            Screen.AuthCodeCheckScreen.route -> TopLevelDestination.CODECHECK
            else -> null
        }

//    val navArgumentList : List<NamedNavArgument> = emptyList()
//        @Composable set(value) = navController.currentBackStackEntry.arguments.set
//    val navArg : String
//        @Composable get() = navController
//            .currentBackStackEntry?.arguments?.getString(DETAIL_ARGUMENT_KEY).default("null")

    fun getStringNavArgument(key : String): String {
        return navController.currentBackStackEntry?.arguments?.getString(key).default("null")
    }


    fun navigateToTopLevelDestination(currentTopLevelDestination: TopLevelDestination) {
        trace("Navigation: ${currentTopLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }


            when (currentTopLevelDestination) {
                TopLevelDestination.AUTH -> navController.navigateToAuthPhone(topLevelNavOptions)
                TopLevelDestination.CODECHECK -> navController.navigateToCodeCheck(getStringNavArgument(DETAIL_ARGUMENT_KEY),topLevelNavOptions)
                TopLevelDestination.REGISTER -> navController.navigateToReg(getStringNavArgument(DETAIL_ARGUMENT_KEY),topLevelNavOptions)
                TopLevelDestination.CHAT -> navController.navigateToChatList(topLevelNavOptions)
                TopLevelDestination.PROFILE -> navController.navigateToProfileScreen(topLevelNavOptions)
            }
        }
    }
    fun <T> T?.default(default: T): T {
        return this ?: default
    }
}