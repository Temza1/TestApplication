package com.example.testapplication.presentation.chatScreen

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(
    onClickReturnButton:() -> Unit
) {
    ChatScreenContent(modifier = Modifier, onClickReturnButton)
}

@Composable
fun ChatScreenContent(modifier: Modifier = Modifier,onClickReturnButton:() -> Unit) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        content = { paddingValues ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {}
        },
        bottomBar = {
            TextField(value = "", onValueChange = {})
        })
}