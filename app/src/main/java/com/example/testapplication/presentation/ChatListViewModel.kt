package com.example.testapplication.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.useCases.SendCodeUseCase
import com.example.testapplication.domain.useCases.SendPhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    application: Application,
    private val sendPhoneUseCase: SendPhoneUseCase,
    private val sendCodeUseCase: SendCodeUseCase
): AndroidViewModel(application),
    ChatListScreenContract.ViewModel {

    private val _state = MutableStateFlow(ChatListScreenContract.State())
    override val state: StateFlow<ChatListScreenContract.State> = _state


    override fun sendEvent(event: ChatListScreenContract.Event) {
        when (event) {
            is ChatListScreenContract.Event.SendPhone -> sendPhone(event.phone)
            is ChatListScreenContract.Event.SendCode -> sendCode(event.phone, event.code)
            else -> {}
        }
    }

    private fun sendPhone(phone: String) {
        viewModelScope.launch {
            val boolean = sendPhoneUseCase.invoke(phone)
            if (boolean) {
                _state.value = _state.value.copy(isAuthPhoneSuccess = true)
            }
        }
    }

    private fun sendCode(phone: String, code: String) {
        viewModelScope.launch {
            val boolean = sendCodeUseCase.invoke(phone, code)
            if (boolean) {
                _state.value = _state.value.copy(isAuthorized = true)
            }
        }
    }


}