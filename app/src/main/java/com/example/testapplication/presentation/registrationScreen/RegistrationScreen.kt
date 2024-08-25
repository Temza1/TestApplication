package com.example.testapplication.presentation.registrationScreen

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testapplication.navigation.Screen
import com.example.testapplication.presentation.authPhoneScreen.AuthPhoneScreenContract
import com.example.testapplication.ui.theme.TestApplicationTheme

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    viewModel: RegScreenViewModel = viewModel(),
    phone : String,
    navController : NavController
) {
    val state by viewModel.state.collectAsState()
    RegScreenContent(
        modifier,
        state = state,
        phone,
        startAuthPhoneScreen = {navController.navigate(route = Screen.AuthPhoneScreen.route)},
        sendUsername = {viewModel.sendEvent(RegScreenContract.Event.SendUsername(it.first,it.second,it.third))}
    )
}

@Composable
fun RegScreenContent(
    modifier: Modifier = Modifier,
    state : RegScreenContract.State,
    phone : String,
    startAuthPhoneScreen:() -> Unit,
    sendUsername:(Triple<String,String,String>) -> Unit
) {
    var nickname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var isButtonClicked by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(phone, textAlign = TextAlign.Center, fontSize = 38.sp,color = Color.Gray)
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(0.dp, 12.dp, 0.dp, 0.dp),
            value = nickname,
            onValueChange = {
                nickname = it
            },
            placeholder = {
                Text(
                    text = "Введите псевдоним",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        )
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(0.dp, 12.dp, 0.dp, 0.dp),
            value = username,
            onValueChange = {
                username = it
            },
            placeholder = {
                Text(
                    text = "Введите имя",
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
                Toast.makeText(context, "в разработке", Toast.LENGTH_SHORT).show()
            }) {
            Text(text = "Продолжить", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    TestApplicationTheme {
        RegScreenContent(
            state = RegScreenContract.State(),
            phone = "",
            startAuthPhoneScreen = {},
            sendUsername = {}
        )
    }
}