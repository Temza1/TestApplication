package com.example.testapplication.presentation.authPhoneScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.ui.theme.TestApplicationTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testapplication.navigation.Screen
import java.util.regex.Pattern


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPhoneScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthPhoneScreenViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    AuthPhoneScreenContent(
        modifier,
        state,
        startCheckCodeScreen = {
            navController.navigate(
                route = Screen.AuthCodeCheckScreen.passPhone(
                    it
                )
            )
        },
        sendPhoneOnClick = { viewModel.sendEvent(AuthPhoneScreenContract.Event.SendPhone(it)) }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPhoneScreenContent(
    modifier: Modifier = Modifier,
    state: AuthPhoneScreenContract.State,
    startCheckCodeScreen: (String) -> Unit,
    sendPhoneOnClick: (String) -> Unit
) {
    var phone by remember { mutableStateOf("") }
    val regex = "([+]7)([0-9]{10})"
    val context = LocalContext.current

    if (state.isAuthPhoneSuccess) {
        startCheckCodeScreen(phone)
    }


    Column(
        modifier = modifier
            .fillMaxHeight(0.5f)
            .padding(28.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Телефон", fontSize = 38.sp, textAlign = TextAlign.Center
        )
        Text(
            "Проверьте код страны и введите номер Вашего телефона",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(28.dp, 16.dp)
            .fillMaxHeight(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(74.dp)
                .padding(0.dp, 12.dp, 0.dp, 0.dp),
            value = phone,
            onValueChange = {
                phone = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(0.dp, 12.dp, 0.dp, 0.dp),
            onClick = {
                if (phone.isNotEmpty()) {
                    if (Pattern.compile(regex).matcher(phone).matches()) {
                        sendPhoneOnClick(phone)
                    } else {
                        Toast.makeText(context, "Номер введён некорректно", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Введите номер телефона", Toast.LENGTH_SHORT).show()
                }
            }) {
            Text(text = "Продолжить", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AuthorizationScreenPreview() {
    TestApplicationTheme {
        AuthPhoneScreenContent(
            state = AuthPhoneScreenContract.State(),
            sendPhoneOnClick = {},
            startCheckCodeScreen = {}
        )
    }
}