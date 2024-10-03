package com.example.testapplication.presentation.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.domain.model.chats.User

@Composable
fun ProfileScreen(
    onClickReturnButton: () -> Unit
) {
    ProfileScreenContent(modifier = Modifier, onClickReturnButton)
}
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    onClickReturnButton: () -> Unit
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .size(0.dp, 250.dp)
        ) {

            IconButton(onClick = { onClickReturnButton() }, modifier.padding(10.dp,22.dp)) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "content description"
                )
            }


        }
        Box(
            modifier = modifier
                .background(color = Color.White)
                .fillMaxSize()
        ) {
            InfoAboutUser(modifier, user)
        }
    }
}

@Composable
fun InfoAboutUser(
    modifier: Modifier,
    worker: User
) {
    Column {
        Row(
            modifier = modifier.padding(20.dp, 20.dp)
        ) {

            Icon(
                Icons.Filled.Star,
                modifier = Modifier.padding(
                    0.dp,0.dp,10.dp,0.dp,
                ),
                contentDescription = "content description"
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    worker.birthday,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    worker.birthday,
                    fontSize = 18.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }

        }

        Row(
            modifier = modifier.padding(20.dp, 0.dp)
        ) {

            Icon(
                Icons.Filled.Call,
                modifier = Modifier.padding(
                    0.dp,0.dp,10.dp,0.dp,
                ),
                contentDescription = "content description"
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    worker.phone,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}