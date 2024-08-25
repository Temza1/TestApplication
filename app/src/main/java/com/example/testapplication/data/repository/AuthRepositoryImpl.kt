package com.example.testapplication.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.testapplication.data.api.AuthApiService
import com.example.testapplication.data.mapper.AuthMapper
import com.example.testapplication.data.model.CodeRequest
import com.example.testapplication.data.model.PhoneRequest
import com.example.testapplication.data.model.UsernameRequest
import com.example.testapplication.domain.repository.AuthRepository
import org.json.JSONObject
import retrofit2.Response
import utils.NAME_PREFERENCE
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    context: Context,
    private val authApiService: AuthApiService,
    private val authMapper: AuthMapper
): AuthRepository {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)
    val editor = preferences.edit()

    override suspend fun sendPhone(phone: String): Boolean {
        val authPhoneDto = doOnError(authApiService.sendAuthCode(PhoneRequest(phone)))
        val domainAuthPhone = authMapper.mapDtoAuthPhoneToDomainAuthPhone(authPhoneDto)
        return domainAuthPhone.isSuccess
    }

    override suspend fun sendPhoneAndCode(phone: String, code: String): Boolean {
        val token = authApiService.checkAuthCode(CodeRequest(phone, code))
        val domainToken = authMapper.mapDtoTokenToDomainToken(doOnError(token))
        if (domainToken.accessToken != null) {
            editor.apply {
                putString(APP_PREF_ACCESS_TOKEN, domainToken.accessToken)
                putString(APP_PREF_REFRESH_TOKEN, domainToken.refreshToken)
            }
        }
        return domainToken.isUserExist
    }

    override suspend fun sendUsername(phone: String, name: String, username: String): Boolean {
        val usernameDto = authApiService.register(UsernameRequest(phone, name, username))
        val domainUsername = authMapper.mapDtoUsernametoDomainUsername(doOnError(usernameDto))
        if (domainUsername.accessToken != null) {
            editor.apply {
                putString(APP_PREF_ACCESS_TOKEN, domainUsername.accessToken)
                putString(APP_PREF_REFRESH_TOKEN, domainUsername.refreshToken)
            }
        }
        return true
    }

    private fun <T> doOnError(res: Response<T>): T {
        if (res.isSuccessful) {
            return res.body()!!
        } else {
            val errorMessage = res.errorBody()?.string()?.let {
                JSONObject(it).getString("error") // or whatever your message is
            } ?: run {
                res.code().toString()
            }
            throw Exception(errorMessage)
        }
    }

    companion object {
        const val APP_PREF_ACCESS_TOKEN = "accessToken"
        const val APP_PREF_REFRESH_TOKEN = "refreshToken"
    }
}