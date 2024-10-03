package com.example.testapplication.presentation.chatListScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.testapplication.R
import com.example.testapplication.ui.theme.TestApplicationTheme

@Composable
fun ChatItem(
//    chatItem : ChatItem
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image("https://avatars.mds.yandex.net/get-entity_search/2487574/434829330/S600xU_2x")
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Название чата", fontWeight = FontWeight.Bold)
            Text(text = "последнее сообщение")
        }
    }
}

@Composable
fun Image(
    image : String
) {
    AsyncImage(
        model = image,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        modifier = Modifier
            .size(84.dp)
            .padding(6.dp)
            .clip(CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun ChatItemPreview() {
    TestApplicationTheme {
        ChatItem(
        )
    }
}