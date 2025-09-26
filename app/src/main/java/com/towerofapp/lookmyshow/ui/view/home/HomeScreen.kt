package com.towerofapp.lookmyshow.ui.view.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Welcome to LookMyShow 🎬", style = MaterialTheme.typography.titleLarge)
    }
}
