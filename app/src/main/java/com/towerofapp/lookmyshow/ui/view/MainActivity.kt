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
import androidx.compose.runtime.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LookMyShowTheme(darkTheme = false) {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = hiltViewModel()
                val state by authViewModel.authState.collectAsState()

                LaunchedEffect(Unit) {
                    authViewModel.checkUser()
                }

                AppNavGraph(navController = navController, startDestination = "login")

                LaunchedEffect(state) {
                    when (state) {
                        is AuthViewModel.AuthState.Success -> {
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                        AuthViewModel.AuthState.Idle -> {
                            navController.navigate("login") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

