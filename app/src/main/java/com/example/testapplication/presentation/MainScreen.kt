package com.example.testapplication.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapplication.presentation.login.AuthPhoneScreen
import com.example.testapplication.ui.theme.TestApplicationTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    TestApplicationTheme {
        MainScreen()
    }
}