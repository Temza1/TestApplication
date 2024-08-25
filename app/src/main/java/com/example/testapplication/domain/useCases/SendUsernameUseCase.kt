package com.example.testapplication.domain.useCases

import com.example.testapplication.data.repository.AuthRepositoryImpl
import javax.inject.Inject

class SendUsernameUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {
    suspend operator fun invoke(phone : String,name : String, username : String) : Boolean {
        return repository.sendUsername(phone, name, username)
    }
}