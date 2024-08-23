package com.example.testapplication.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.presentation.ChatListViewModel
import com.example.testapplication.presentation.MaskVisualTransformation
import com.example.testapplication.ui.theme.TestApplicationTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapplication.R
import com.example.testapplication.presentation.ChatListScreenContract
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPhoneScreen(
    modifier: Modifier = Modifier,
    startAuthCodeCheckScreen: () -> Unit,
    viewModel: ChatListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    AuthPhoneScreenContent(modifier, state, startAuthCodeCheckScreen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPhoneScreenContent(
    modifier: Modifier = Modifier,
    state: ChatListScreenContract.State,
    startAuthCodeCheckScreen: () -> Unit,
) {
    var phone by remember { mutableStateOf("") }
    val mask = MaskVisualTransformation("+7 (___) ___-__-__")

    if (state.isAuthPhoneSuccess) {
        startAuthCodeCheckScreen()
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            visualTransformation = mask
        )
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(0.dp, 12.dp, 0.dp, 0.dp),
            onClick = {
                ChatListScreenContract.Event.SendPhone(phone)
            }) {
            Text(text = "Продолжить", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBox(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier.wrapContentSize(Alignment.BottomEnd)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorizationScreenPreview() {
    TestApplicationTheme {
        AuthPhoneScreenContent(
            state = ChatListScreenContract.State(),
            startAuthCodeCheckScreen = {}
        )
    }
}