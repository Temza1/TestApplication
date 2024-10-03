package com.example.testapplication.domain.useCases

import com.example.testapplication.data.handleErrorNetwork.ApiResult
import com.example.testapplication.domain.model.AuthResult
import com.example.testapplication.domain.model.Token
import com.example.testapplication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendCodeUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(phone: String, code: String): Flow<ApiResult<*>> {
        return repository.sendPhoneAndCode(phone, code)
    }
}