package com.towerofapp.lookmyshow.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.towerofapp.lookmyshow.ui.navigation.AppNavGraph
import com.towerofapp.lookmyshow.ui.theme.LookMyShowTheme
import com.towerofapp.lookmyshow.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LookMyShowTheme(darkTheme = false) {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = hiltViewModel()

                // Checking if user is logged in
                LaunchedEffect(Unit) {
                    authViewModel.checkUser() // setting authState to Success if already logged in
                }

                val startDestination = if (authViewModel.authState.collectAsState().value is AuthViewModel.AuthState.Success) {
                    "home"
                } else {
                    "login"
                }

                AppNavGraph(navController,startDestination)
            }
        }
    }
}
