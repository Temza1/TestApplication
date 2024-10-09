package com.example.testapplication.presentation.authCodeCheckScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.handleErrorNetwork.ApiStatus
import com.example.testapplication.domain.useCases.SendCodeUseCase
import com.example.testapplication.domain.useCases.SendPhoneUseCase
import com.example.testapplication.domain.useCases.SendUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
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
            sendCodeUseCase(phone, code)
                .collect { result ->
                    when (result.status) {
                        ApiStatus.SUCCESS -> {
                            if(result.data == true) {
                                _state.value = _state.value.copy(isAuthorized = true)
                            }
                        }
                        ApiStatus.ERROR -> {
                            _state.value = _state.value.copy(error = result.message)
                        }
                        else -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }

    companion object {
        const val LOG_CHAT_LIST_VM = "ChatListViewModel"
    }
}