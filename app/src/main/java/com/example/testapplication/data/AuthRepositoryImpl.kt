package com.example.testapplication.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.testapplication.data.mapper.Mapper
import com.example.testapplication.domain.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import retrofit2.Response

class AuthRepositoryImpl(private val application: Application) : AuthRepository {

    val preferences: SharedPreferences =
        application.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    val editor = preferences.edit()

    private val authApiService = AuthApiFactory.create()
    private val mapper = Mapper()

    override suspend fun sendPhone(phone: String): Boolean {
        val authPhoneDto = doOnError(authApiService.sendAuthCode(phone))
        val domainAuthPhone = mapper.mapDtoAuthPhoneToDomainAuthPhone(authPhoneDto)
        return domainAuthPhone.isSuccess
    }

    override suspend fun sendPhoneAndCode(phone: String, code: String): Boolean {
        val token = authApiService.checkAuthCode(phone, "133337")
        val domainToken = mapper.mapDtoTokenToDomainToken(doOnError(token))
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