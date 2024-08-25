package com.example.testapplication.presentation.authCodeCheckScreen

import kotlinx.coroutines.flow.StateFlow

interface AuthCodeCheckScreenContract {
    sealed class Event {
        data class SendCode(val phone: String, val code: String) : Event()
    }

    data class State(
        val isAuthorized: Boolean = false
    )

    interface ViewModel {
        val state: StateFlow<State>
        fun sendEvent(event: Event)
    }
}