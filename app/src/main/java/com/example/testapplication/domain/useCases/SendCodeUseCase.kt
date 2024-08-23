package com.example.testapplication.domain.useCases

import com.example.testapplication.domain.repository.AuthRepository
import javax.inject.Inject

class SendCodeUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(phone: String, code: String): Boolean {
        return repository.sendPhoneAndCode(phone, code)
    }
}