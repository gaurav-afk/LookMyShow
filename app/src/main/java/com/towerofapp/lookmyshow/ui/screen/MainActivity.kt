package com.towerofapp.lookmyshow.ui.screen

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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            LookMyShowTheme(darkTheme = false) {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = hiltViewModel()
                val state by authViewModel.authState.collectAsState()
                LaunchedEffect(Unit) {
                    authViewModel.checkUser()
                }
                LaunchedEffect(state) {
                    when (state) {
                        is AuthViewModel.AuthState.Success -> {
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                        AuthViewModel.AuthState.Idle       -> {
                            navController.navigate("login") {
                                popUpTo("home") { inclusive = true }
                                launchSingleTop = true
                            }
                        }

                        else                               -> {}
                    }
                }
                AppNavGraph(navController = navController, startDestination = "login")
            }
        }
    }
}