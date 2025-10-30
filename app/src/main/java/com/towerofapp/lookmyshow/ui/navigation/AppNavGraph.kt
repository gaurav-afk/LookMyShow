package com.towerofapp.lookmyshow.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.towerofapp.lookmyshow.core.AppConfig
import com.towerofapp.lookmyshow.ui.screen.auth.LoginScreen
import com.towerofapp.lookmyshow.ui.screen.auth.SignUpScreen
import com.towerofapp.lookmyshow.ui.screen.home.BookedTicketsScreen
import com.towerofapp.lookmyshow.ui.screen.home.BookingScreen
import com.towerofapp.lookmyshow.ui.screen.home.BookingSuccessScreen
import com.towerofapp.lookmyshow.ui.screen.home.HallLayoutScreen
import com.towerofapp.lookmyshow.ui.screen.home.HomeScreen
import com.towerofapp.lookmyshow.ui.screen.home.LoadingScreen
import com.towerofapp.lookmyshow.ui.screen.home.TheatersScreen

@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController, startDestination = startDestination) {

        composable("login") {
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate("signup") {
                        popUpTo("login") { inclusive = true }
                    }
                }, onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                })
        }

        composable("signup") {
            SignUpScreen(onNavigateToLogin = {
                navController.navigate("login") {
                    popUpTo("signup") { inclusive = true }
                }
            }, onNavigateToHome = {
                navController.navigate("home") {
                    popUpTo("signup") { inclusive = true }
                }
            })
        }

        composable("home") { HomeScreen(navController) }

        composable("loading") { LoadingScreen() }

        composable("theatres/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            TheatersScreen(
                movieId = movieId,
                onPopBackStack = { navController.popBackStack() },
                onNavigateToHallLayout = { movieIdParam, safeTimeParam, encodedTitleParam, theatreNameParam ->
                    navController.navigate("hall/$movieIdParam/$safeTimeParam/$encodedTitleParam/$theatreNameParam")
                })
        }

        composable("hall/{movieId}/{timeSlot}/{movieTitle}/{theater}") { backStackEntry ->
            val timeSlot = backStackEntry.arguments?.getString("timeSlot") ?: ""
            val theater = backStackEntry.arguments?.getString("theater") ?: ""
            val movieTitle = backStackEntry.arguments?.getString("movieTitle")?.let {
                java.net.URLDecoder.decode(it, "UTF-8")
            } ?: ""
            HallLayoutScreen(
                onPopBackStack = {navController.popBackStack()},
                onNavigateToBooking = { movieTitle, selectedSeats, theater, decodedTimeSlot ->
                    navController.navigate("booking/$movieTitle/${selectedSeats}/$theater/$decodedTimeSlot")
                },
                timeSlot = timeSlot,
                movieTitle = movieTitle,
                theater = theater
            )
        }

        composable(route = "booking/{movieTitle}/{selectedSeats}/{theater}/{timing}") { backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getString("movieTitle") ?: ""
            val theater = backStackEntry.arguments?.getString("theater") ?: ""
            val seats =
                backStackEntry.arguments?.getString("selectedSeats")?.split(",") ?: emptyList()
            val timing = backStackEntry.arguments?.getString("timing") ?: "N/A"
            BookingScreen(
                onPopBackStack = {navController.popBackStack()},
                onNavigateToSuccess = {
                    navController.navigate("success/$movieTitle/${seats.joinToString(",")}/$theater/${AppConfig.ticketPrice * seats.size + AppConfig.convenienceFee}/$timing")
                },
                movieTitle = movieTitle,
                seats = seats,
                theater = theater,
                timing = timing
            )
        }
        composable(route = "success/{movieTitle}/{selectedSeats}/{theater}/{price}/{timing}") { backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getString("movieTitle") ?: "N/A"
            val theater = backStackEntry.arguments?.getString("theater") ?: "N/A"
            val seats =
                backStackEntry.arguments?.getString("selectedSeats")?.split(",") ?: emptyList()
            val price = backStackEntry.arguments?.getString("price") ?: "N/A"
            val timing = backStackEntry.arguments?.getString("timing") ?: "N/A"
            BookingSuccessScreen(
                onNavigateToHome = { navController.navigate("home") { popUpTo(0) } },
                movieTitle = movieTitle,
                bookedSeats = seats,
                theater = theater,
                totalPrice = price,
                timing = timing
            )
        }
        composable(route = "bookedTickets") { BookedTicketsScreen(onPopBackStack = { navController.popBackStack() }) }
    }
}
