package com.example.testapplication.data.mapper

import com.example.testapplication.data.model.PhoneDto
import com.example.testapplication.data.model.TokenDto
import com.example.testapplication.data.model.UsernameDto
import com.example.testapplication.domain.model.Phone
import com.example.testapplication.domain.model.Token
import com.example.testapplication.domain.model.Username

class AuthMapper {
    fun mapDtoTokenToDomainToken(tokenDto: TokenDto): Token {
        val domainToken = Token(
            tokenDto.accessToken ?: "default",
            tokenDto.refreshToken ?: "default",
            tokenDto.userId.default(-1),
            tokenDto.isUserExist
        )
        return domainToken
    }

    fun mapDtoAuthPhoneToDomainAuthPhone(authPhone: PhoneDto): Phone {
        val domainPhone = Phone(
            authPhone.isSuccess
        )
        return domainPhone
    }

    fun mapDtoUsernametoDomainUsername(username: UsernameDto): Username {
        val usernameDomain = Username(
            username.accessToken,
            username.refreshToken,
            username.userId
        )
        return usernameDomain
    }

    fun <T> T?.default(default: T): T {
        return this ?: default
    }
}