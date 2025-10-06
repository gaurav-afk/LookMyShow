package com.towerofapp.lookmyshow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.towerofapp.lookmyshow.ui.view.auth.LoginScreen
import com.towerofapp.lookmyshow.ui.view.auth.SignUpScreen
import com.towerofapp.lookmyshow.ui.view.home.HomeScreen
import com.towerofapp.lookmyshow.ui.view.home.TheatersScreen

@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController, startDestination = startDestination) {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("theatres/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            TheatersScreen(movieId = movieId, navController = navController)
        }
    }
}
