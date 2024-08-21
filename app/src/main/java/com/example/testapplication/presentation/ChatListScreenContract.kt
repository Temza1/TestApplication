package com.example.testapplication.presentation

import com.example.testapplication.domain.AuthRepository
import com.example.testapplication.domain.useCases.SendCodeUseCase
import com.example.testapplication.domain.useCases.SendPhoneUseCase
import kotlinx.coroutines.flow.StateFlow

interface ChatListScreenContract {
    sealed class Event {
        data object LoadUserInfo : Event()
        data class SendPhone(val phone: String) : Event()
        data class SendCode(val phone: String, val code: String) : Event()
    }

    data class State(
        val isAuthPhoneSuccess : Boolean = false,
        val isAuthorized : Boolean = false,
        val isRegSuccess : Boolean = false
    )

    interface ViewModel {
        val state: StateFlow<State>
        fun sendEvent(event: Event)
    }
}