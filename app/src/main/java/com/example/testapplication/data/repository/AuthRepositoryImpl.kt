package com.example.testapplication.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.testapplication.data.api.AuthApiService
import com.example.testapplication.data.mapper.AuthMapper
import com.example.testapplication.domain.repository.AuthRepository
import org.json.JSONObject
import retrofit2.Response
import utils.CONST_NAME_PREFERENCE
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    application: Application,
    private val authApiService: AuthApiService,
    private val authMapper: AuthMapper
): AuthRepository {

    private val preferences: SharedPreferences =
        application.getSharedPreferences(CONST_NAME_PREFERENCE, Context.MODE_PRIVATE)
    val editor = preferences.edit()

    override suspend fun sendPhone(phone: String): Boolean {
        val authPhoneDto = doOnError(authApiService.sendAuthCode(phone))
        val domainAuthPhone = authMapper.mapDtoAuthPhoneToDomainAuthPhone(authPhoneDto)
        return domainAuthPhone.isSuccess
    }

    override suspend fun sendPhoneAndCode(phone: String, code: String): Boolean {
        val token = authApiService.checkAuthCode(phone, "133337")
        val domainToken = authMapper.mapDtoTokenToDomainToken(doOnError(token))
        editor.apply {
            putString(APP_PREF_ACCESS_TOKEN, domainToken.successToken)
            putString(APP_PREF_REFRESH_TOKEN, domainToken.refreshToken)
        }
        return domainToken.isUserExist
    }

    override suspend fun checkJWT(): String {
        TODO("Not yet implemented")
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