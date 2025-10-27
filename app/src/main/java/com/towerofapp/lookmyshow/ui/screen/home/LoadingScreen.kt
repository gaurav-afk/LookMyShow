package com.towerofapp.lookmyshow.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoadingScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator(color = Color.Red, strokeWidth = 4.dp)
    }
}