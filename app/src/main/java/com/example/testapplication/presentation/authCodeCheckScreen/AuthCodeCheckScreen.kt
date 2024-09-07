package com.example.testapplication.presentation.authCodeCheckScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testapplication.navigation.Screen
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreenContract
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreenViewModel
import com.example.testapplication.ui.theme.TestApplicationTheme

@Composable
fun AuthCodeCheckScreen(
    startRegScreen: (String) -> Unit,
    startChatListScreen: () -> Unit,
    viewModel: AuthCodeCheckScreenViewModel = hiltViewModel(),
    phone : String
) {
    val state by viewModel.state.collectAsState()
    AuthCodeCheckScreenContent(
        modifier = Modifier,
        startRegScreen = startRegScreen,
        startChatListScreen = startChatListScreen,
        sendPhoneAndCode = {viewModel.sendEvent(AuthCodeCheckScreenContract.Event.SendCode(it.first,it.second))},
        state = state,
        phone
        )
}

@Composable
fun AuthCodeCheckScreenContent(
    modifier: Modifier = Modifier,
    startRegScreen: (String) -> Unit,
    startChatListScreen: () -> Unit,
    sendPhoneAndCode:(Pair<String,String>) -> Unit,
    state : AuthCodeCheckScreenContract.State,
    phone : String
) {
    var code by remember { mutableStateOf("") }
    var isButtonClicked by remember { mutableStateOf(false) }

    if(isButtonClicked) {
        if (state.isAuthorized) {
            startChatListScreen()
        } else {
            startRegScreen(phone)
        }
        isButtonClicked = false
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Введите проверочный смс-код, присланный на номер $phone",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(0.dp, 12.dp, 0.dp, 0.dp),
            value = code,
            onValueChange = {
                code = it.filter { it.isDigit() }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            placeholder = {
                Text(
                    text = "",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        )
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(0.dp, 12.dp, 0.dp, 0.dp),
            onClick = {
                sendPhoneAndCode(Pair(phone,code))
                isButtonClicked = true
            }) {
            Text(text = "Отправить")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationScreenPreview() {
    TestApplicationTheme {
        AuthCodeCheckScreenContent(
            startRegScreen = {},
            startChatListScreen = {},
            state = AuthCodeCheckScreenContract.State(),
            sendPhoneAndCode = {},
            phone = ""
        )
    }
}