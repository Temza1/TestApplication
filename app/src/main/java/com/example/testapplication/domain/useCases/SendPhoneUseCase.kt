package com.example.testapplication.domain.useCases

import com.example.testapplication.domain.repository.AuthRepository
import javax.inject.Inject

class SendPhoneUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(phone: String): Boolean {
        return repository.sendPhone(phone)
    }
}