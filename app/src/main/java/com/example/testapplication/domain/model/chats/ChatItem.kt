package com.example.testapplication.domain.model.chats

data class ChatItem(
    val user : User,
    val messages : List<String>
)
