package com.example.testapplication.navigation

const val DETAIL_ARGUMENT_KEY = "phone"

sealed class Screen(val route: String) {

    data object AuthPhoneScreen : Screen(
        route = "auth_phone_screen"
    )

    data object AuthCodeCheckScreen : Screen(
        route = "auth_code_check_screen/{$DETAIL_ARGUMENT_KEY}"
    ) {
        fun passPhone(phone: String): String {
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = phone)
        }
    }

    data object RegistrationScreen : Screen(
        route = "reg_screen/{$DETAIL_ARGUMENT_KEY}"
    ) {
        fun passPhone(phone: String): String {
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = phone)
        }
    }
}