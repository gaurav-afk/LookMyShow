package com.towerofapp.lookmyshow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.towerofapp.lookmyshow.ui.view.auth.LoginScreen
import com.towerofapp.lookmyshow.ui.view.auth.SignUpScreen
import com.towerofapp.lookmyshow.ui.view.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
    }
}
