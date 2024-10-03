package com.example.testapplication.presentation.chatListScreen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapplication.ui.theme.TestApplicationTheme

@Composable
fun ChatListScreen(
    onClickProfileButton:() -> Unit
) {
    ChatListScreenContent(modifier = Modifier,onClickProfileButton)
}

@Composable
fun ChatListScreenContent(
    modifier: Modifier = Modifier,
    onClickProfileButton:() -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                    .height(70.dp)
            ) {
                IconButton(
                    modifier = modifier.size(70.dp,70.dp),
                    onClick = { onClickProfileButton() }
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "content description",
                        modifier = modifier.size(45.dp),
                        Color.Gray
                    )
                }
            }
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
//               items(chatList = chatList) { chatItem -> }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun ChatListScreenPreview() {
    TestApplicationTheme {
        ChatListScreenContent(
            onClickProfileButton = {}
        )
    }
}