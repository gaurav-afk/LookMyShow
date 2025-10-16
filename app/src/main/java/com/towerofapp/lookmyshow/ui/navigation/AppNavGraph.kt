package com.towerofapp.lookmyshow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.towerofapp.lookmyshow.ui.view.auth.LoginScreen
import com.towerofapp.lookmyshow.ui.view.auth.SignUpScreen
import com.towerofapp.lookmyshow.ui.view.home.BookingScreen
import com.towerofapp.lookmyshow.ui.view.home.HallLayoutScreen
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
        composable("hall/{movieId}/{timeSlot}/{movieTitle}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            val timeSlot = backStackEntry.arguments?.getString("timeSlot") ?: ""
            val movieTitle = backStackEntry.arguments?.getString("movieTitle")?.let {
                java.net.URLDecoder.decode(it, "UTF-8")
            } ?: ""
            HallLayoutScreen(navController = navController, movieId = movieId, timeSlot = timeSlot, movieTitle = movieTitle)
        }
        composable(route = "booking/{movieTitle}/{selectedSeats}"){backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getString("movieTitle") ?: ""
            val seats = backStackEntry.arguments?.getString("selectedSeats")?.split(",") ?: emptyList()
            BookingScreen(navController = navController, movieTitle=movieTitle, seats= seats)
        }
    }
}
