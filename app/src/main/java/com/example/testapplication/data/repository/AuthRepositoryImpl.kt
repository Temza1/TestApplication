package com.example.testapplication.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.testapplication.data.api.AuthApiService
import com.example.testapplication.data.handleErrorNetwork.ApiResult
import com.example.testapplication.data.handleErrorNetwork.ApiResult.Success
import com.example.testapplication.data.mapper.AuthMapper
import com.example.testapplication.data.model.CodeRequest
import com.example.testapplication.data.model.PhoneRequest
import com.example.testapplication.data.model.UsernameRequest
import com.example.testapplication.data.model.error.Detail
import com.example.testapplication.data.model.error.ErrorResponce
import com.example.testapplication.domain.repository.AuthRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import utils.NAME_PREFERENCE
import java.io.IOException
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

//    override suspend fun sendPhoneAndCode(phone: String, code: String) = flow {
//        val responce = authApiService.checkAuthCode(CodeRequest(phone, code))
//        val token = authMapper.mapDtoTokenToDomainToken(responce)
//        editor.apply {
//            putString(APP_PREF_ACCESS_TOKEN, token.accessToken)
//            putString(APP_PREF_REFRESH_TOKEN, token.refreshToken)
//        }
//        emit(token)
//    }

    override suspend fun sendPhoneAndCode(phone: String, code: String): Flow<ApiResult<*>> = flow {
        emit(ApiResult.Loading(null,isLoading = true))
        val response = authApiService.checkAuthCode(CodeRequest(phone, code))
        if (response.isSuccessful) {
            val result = response.body()?.let { authMapper.mapDtoTokenToDomainToken(it) }
            if (result != null) {
                emit(Success(result.isUserExist))
            }
//            editor.apply {
//                putString(APP_PREF_ACCESS_TOKEN, result.accessToken)
//                putString(APP_PREF_REFRESH_TOKEN, token.refreshToken)
//            }
        } else {
            val errorMsg = response.errorBody()?.string() ?: run { response.code().toString() }
            response.errorBody()?.close()  // remember to close it after getting the stream of error body
            emit(ApiResult.Error(errorMsg))
        }
    }

    override suspend fun sendUsername(phone: String, name: String, username: String): Flow<ApiResult<*>> = flow {
//        if (domainUsername.accessToken != null) {
//            editor.apply {
//                putString(APP_PREF_ACCESS_TOKEN, domainUsername.accessToken)
//                putString(APP_PREF_REFRESH_TOKEN, domainUsername.refreshToken)
//            }
//        }
        emit(ApiResult.Loading(null,isLoading = true))
        val response = authApiService.register(UsernameRequest(phone, name, username))
        if (response.isSuccessful) {
            val result = response.body()?.let { authMapper.mapDtoUsernametoDomainUsername(it) }
            if (result != null) {
                emit(Success(true))
            }
        } else {
            val gson = Gson()
            val errorResponce = gson.fromJson(response.errorBody()?.string(), ErrorResponce::class.java)
            response.errorBody()?.close()  // remember to close it after getting the stream of error body
            emit(ApiResult.Error(errorResponce.detail.message))
        }
    }

    fun parseError(responseBody: ResponseBody): String {
        val gson = Gson()
        val errorResponse = gson.fromJson(responseBody.string(), Detail::class.java)
        return errorResponse.message
    }

//    fun <T> handleResponce(responce : Class<T>): ApiResult<T> {
//        if (responce.)
//    }

//    fun <T> error(res: Response<T>): ApiResult<T> {
//        return if(res.isSuccessful) {
//            ApiResult.Success(res.body())
//        } else if (res.code() == 400) {
//            ApiResult.Error("Такой пользователь уже зарегистрирован")
//        } else {
//            val errorText = res.errorBody()?.string()?.let {
//                JSONObject(it).getString("error") // or whatever your message is
//            } ?: run {
//                res.code().toString()
//            }
//            return ApiResult.Error(errorText)
//        }
//    }

    private fun <T> doOnError(res: Response<T>): T {
        if (res.isSuccessful) {
            return res.body()!!
        } else {
            val errorMessage = res.errorBody()?.string()?.let {
                JSONObject(it).getString("com/example/testapplication/data/model/error") // or whatever your message is
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