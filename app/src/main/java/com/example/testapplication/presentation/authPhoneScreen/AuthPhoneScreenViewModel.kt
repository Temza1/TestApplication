package com.example.testapplication.presentation.authPhoneScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.useCases.SendCodeUseCase
import com.example.testapplication.domain.useCases.SendPhoneUseCase
import com.example.testapplication.domain.useCases.SendUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthPhoneScreenViewModel @Inject constructor(
    application: Application,
    private val sendPhoneUseCase: SendPhoneUseCase
): AndroidViewModel(application),
    AuthPhoneScreenContract.ViewModel {

    private val _state = MutableStateFlow(AuthPhoneScreenContract.State())
    override val state: StateFlow<AuthPhoneScreenContract.State> = _state


    override fun sendEvent(event: AuthPhoneScreenContract.Event) {
        when (event) {
            is AuthPhoneScreenContract.Event.SendPhone -> sendPhone(event.phone)
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

    companion object {
        const val LOG_CHAT_LIST_VM = "ChatListViewModel"
    }


}