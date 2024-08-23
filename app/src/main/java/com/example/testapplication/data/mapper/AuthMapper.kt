package com.example.testapplication.data.mapper

import com.example.testapplication.data.model.AuthPhoneDto
import com.example.testapplication.data.model.TokenDto
import com.example.testapplication.domain.model.AuthPhone
import com.example.testapplication.domain.model.Token

class AuthMapper {
    fun mapDtoTokenToDomainToken(tokenDto: TokenDto): Token {
        val domainToken = Token(
            tokenDto.successToken,
            tokenDto.refreshToken,
            tokenDto.userId,
            tokenDto.isUserExist
        )
        return domainToken
    }
    fun mapDtoAuthPhoneToDomainAuthPhone(authPhone: AuthPhoneDto): AuthPhone {
        val domainAuthPhone = AuthPhone(
            authPhone.isSuccess
        )
        return domainAuthPhone
    }
}