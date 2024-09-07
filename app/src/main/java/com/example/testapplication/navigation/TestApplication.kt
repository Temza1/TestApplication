package com.example.testapplication.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TestApplication(appState: TestApplicationState) {
    Scaffold { paddingValues ->
        ChatNavHost(appState = appState, modifier = Modifier.padding(paddingValues))
    }
}