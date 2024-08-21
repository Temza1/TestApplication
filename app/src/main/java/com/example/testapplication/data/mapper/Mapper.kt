package com.example.testapplication.data.mapper

import com.example.testapplication.data.AuthPhoneDto
import com.example.testapplication.data.TokenDto
import com.example.testapplication.domain.AuthPhone
import com.example.testapplication.domain.Token

class Mapper {

    fun mapDtoTokenToDomainToken(tokenDto : TokenDto) : Token {
        val domainToken = Token(
            tokenDto.successToken,
            tokenDto.refreshToken,
            tokenDto.userId,
            tokenDto.isUserExist
        )
        return domainToken
    }

    fun mapDtoAuthPhoneToDomainAuthPhone(authPhone : AuthPhoneDto) : AuthPhone {
        val domainAuthPhone = AuthPhone(
            authPhone.isSuccess
        )
        return domainAuthPhone
    }

}