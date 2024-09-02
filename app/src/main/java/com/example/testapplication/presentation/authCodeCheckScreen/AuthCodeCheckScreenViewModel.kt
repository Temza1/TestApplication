package com.example.testapplication.presentation.authCodeCheckScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.useCases.SendCodeUseCase
import com.example.testapplication.domain.useCases.SendPhoneUseCase
import com.example.testapplication.domain.useCases.SendUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthCodeCheckScreenViewModel @Inject constructor(
    application: Application,
    private val sendCodeUseCase: SendCodeUseCase
): AndroidViewModel(application),
    AuthCodeCheckScreenContract.ViewModel {

    private val _state = MutableStateFlow(AuthCodeCheckScreenContract.State())
    override val state: StateFlow<AuthCodeCheckScreenContract.State> = _state

    override fun sendEvent(event: AuthCodeCheckScreenContract.Event) {
        when (event) {
            is AuthCodeCheckScreenContract.Event.SendCode -> sendCode(event.phone, event.code)
            else -> {}
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

    companion object {
        const val LOG_CHAT_LIST_VM = "ChatListViewModel"
    }
}