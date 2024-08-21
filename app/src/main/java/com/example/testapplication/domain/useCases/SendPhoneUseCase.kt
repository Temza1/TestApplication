package com.example.testapplication.domain.useCases

import com.example.testapplication.domain.AuthRepository

class SendPhoneUseCase(private val repository : AuthRepository) {

    suspend operator fun invoke(phone : String) : Boolean {
        return repository.sendPhone(phone)
    }
}