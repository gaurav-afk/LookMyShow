package com.towerofapp.lookmyshow.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.towerofapp.lookmyshow.ui.navigation.AppNavGraph
import com.towerofapp.lookmyshow.ui.theme.LookMyShowTheme
import com.towerofapp.lookmyshow.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

                @SuppressLint("RestrictedApi")
                LaunchedEffect(Unit) {
                    navController.currentBackStack.collect { entries ->
                        val routes = entries.map { it.destination.route }
                        Log.d("Navigation", "Current backstack: $routes")
                    }
                }

                LaunchedEffect(Unit) {
                    authViewModel.checkUser()
                }
                LaunchedEffect(state) {
                    when (state) {
                        is AuthViewModel.AuthState.Success -> {
                            navController.navigate("home") {
                                popUpTo("loading") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                        AuthViewModel.AuthState.Idle       -> {
                            navController.navigate("login") {
                                popUpTo(navController.graph.id) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                        else                               -> {}
                    }
                }
                androidx.compose.material3.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
                ) {
                    AppNavGraph(navController = navController, startDestination = "loading")
                }
            }
        }
    }
}