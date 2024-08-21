package com.example.testapplication.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.AuthRepositoryImpl
import com.example.testapplication.domain.AuthRepository
import com.example.testapplication.domain.useCases.SendCodeUseCase
import com.example.testapplication.domain.useCases.SendPhoneUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatListViewModel(application: Application) : AndroidViewModel(application),
    ChatListScreenContract.ViewModel {

    private val _state = MutableStateFlow(ChatListScreenContract.State())
    override val state: StateFlow<ChatListScreenContract.State> = _state

    private val authRepositoryImpl: AuthRepository = AuthRepositoryImpl(application)
    private val sendPhoneUseCase = SendPhoneUseCase(authRepositoryImpl)
    private val sendCodeUseCase = SendCodeUseCase(authRepositoryImpl)

    override fun sendEvent(event: ChatListScreenContract.Event) {
        when (event) {
            is ChatListScreenContract.Event.SendPhone -> SendPhone(event.phone)
            is ChatListScreenContract.Event.SendCode -> SendCode(event.phone,event.code)
            else -> {}
        }
    }

    private fun SendPhone(phone: String) {
        viewModelScope.launch {
            val boolean = sendPhoneUseCase.invoke(phone)
            if (boolean) {
                _state.value = _state.value.copy(isAuthPhoneSuccess = true)
            }
        }
    }

    private fun SendCode(phone: String, code: String) {
        viewModelScope.launch {
            val boolean = sendCodeUseCase.invoke(phone,code)
            if (boolean) {
                _state.value = _state.value.copy(isAuthorized = true)
            }
        }
    }


}