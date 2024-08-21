package com.example.testapplication.domain.useCases

import com.example.testapplication.domain.AuthRepository

class SendCodeUseCase (private val repository : AuthRepository) {
    suspend operator fun invoke(phone : String,code : String) : Boolean {
        return repository.sendPhoneAndCode(phone, code)
    }
}