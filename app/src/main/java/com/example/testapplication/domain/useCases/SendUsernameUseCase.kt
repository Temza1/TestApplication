package com.example.testapplication.domain.useCases

import com.example.testapplication.data.handleErrorNetwork.ApiResult
import com.example.testapplication.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendUsernameUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {
    suspend operator fun invoke(phone : String,name : String, username : String) : Flow<ApiResult<*>> {
        return repository.sendUsername(phone, name, username)
    }
}