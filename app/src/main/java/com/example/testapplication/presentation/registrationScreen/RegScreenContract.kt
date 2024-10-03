package com.example.testapplication.presentation.registrationScreen

import kotlinx.coroutines.flow.StateFlow

interface RegScreenContract {
    sealed class Event {
        data class SendUsername(val phone : String,val name : String,val username : String) : Event()
    }

    data class State(
        val isRegSuccess : Boolean = false,
        val isLoading: Boolean = false,
        val error: String? = null
    )

    interface ViewModel {
        val state: StateFlow<State>
        fun sendEvent(event: Event)
    }
}