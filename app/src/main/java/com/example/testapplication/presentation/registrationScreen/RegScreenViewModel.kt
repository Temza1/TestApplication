package com.example.testapplication.presentation.registrationScreen

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegScreenViewModel @Inject constructor(
    application: Application,
    private val sendUsernameUseCase: SendUsernameUseCase
): AndroidViewModel(application),
    RegScreenContract.ViewModel {

    private val _state = MutableStateFlow(RegScreenContract.State())
    override val state: StateFlow<RegScreenContract.State> = _state


    override fun sendEvent(event: RegScreenContract.Event) {
        when (event) {
            is RegScreenContract.Event.SendUsername -> sendUsername(event.phone,event.name,event.username)
            else -> {}
        }
    }

    private fun sendUsername(phone : String,name : String, username : String) {
        viewModelScope.launch {
            sendUsernameUseCase.invoke(phone, name, username).collect { result ->
                when (result.status) {
                    ApiStatus.SUCCESS -> {
                        _state.value = _state.value.copy(isRegSuccess = true)
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