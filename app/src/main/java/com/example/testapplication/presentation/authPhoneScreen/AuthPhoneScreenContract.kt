package com.example.testapplication.presentation.authPhoneScreen

import kotlinx.coroutines.flow.StateFlow

interface AuthPhoneScreenContract {
    sealed class Event {
        data class SendPhone(val phone: String): Event()
    }

    data class State(
        val isAuthPhoneSuccess: Boolean = false
    )

    interface ViewModel {
        val state: StateFlow<State>
        fun sendEvent(event: Event)
    }
}