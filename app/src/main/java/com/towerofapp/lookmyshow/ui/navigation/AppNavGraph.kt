package com.towerofapp.lookmyshow.ui.navigation

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
        composable(route = Screen.Login.route) {
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }, onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
                })
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(onNavigateToLogin = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.SignUp.route) { inclusive = true }
                }
            }, onNavigateToHome = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.SignUp.route) { inclusive = true }
                }
            })
        }

        composable(route = Screen.Home.route) { HomeScreen(navController) }

        composable(route = Screen.Loading.route) { LoadingScreen() }

        composable(route = Screen.Theatres.route) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            TheatersScreen(
                movieId = movieId,
                onPopBackStack = { navController.popBackStack() },
                onNavigateToHallLayout = { movieIdParam, safeTimeParam, encodedTitleParam, theatreNameParam ->
                    navController.navigate( route = Screen.Hall.createRoute(
                        movieIdParam,
                        safeTimeParam,
                        encodedTitleParam,
                        theatreNameParam
                    ))
                })
        }

        composable(route = Screen.Hall.route) { backStackEntry ->
            val timeSlot = backStackEntry.arguments?.getString("timeSlot") ?: ""
            val theater = backStackEntry.arguments?.getString("theater") ?: ""
            val movieTitle = backStackEntry.arguments?.getString("movieTitle")?.let {
                java.net.URLDecoder.decode(it, "UTF-8")
            } ?: ""
            HallLayoutScreen(
                onPopBackStack = { navController.popBackStack() },
                onNavigateToBooking = { title, selectedSeats, theaterName, timing ->
                    navController.navigate(route = Screen.Booking.createRoute(title, selectedSeats, theaterName, timing))
                },
                timeSlot = timeSlot,
                movieTitle = movieTitle,
                theater = theater
            )
        }


        composable(route = Screen.Booking.route) { backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getString("movieTitle") ?: ""
            val theater = backStackEntry.arguments?.getString("theater") ?: ""
            val seats =
                backStackEntry.arguments?.getString("selectedSeats")?.split(",") ?: emptyList()
            val timing = backStackEntry.arguments?.getString("timing") ?: "N/A"
            BookingScreen(
                onPopBackStack = { navController.popBackStack() },
                onNavigateToSuccess = {
                    val totalPrice = AppConfig.ticketPrice * seats.size + AppConfig.convenienceFee
                    navController.navigate(route = Screen.Success.createRoute(movieTitle, seats, theater, totalPrice.toDouble(), timing))
                },
                movieTitle = movieTitle,
                seats = seats,
                theater = theater,
                timing = timing
            )
        }

        composable(route = Screen.Success.route) { backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getString("movieTitle") ?: "N/A"
            val theater = backStackEntry.arguments?.getString("theater") ?: "N/A"
            val seats =
                backStackEntry.arguments?.getString("selectedSeats")?.split(",") ?: emptyList()
            val price = backStackEntry.arguments?.getString("price") ?: "N/A"
            val timing = backStackEntry.arguments?.getString("timing") ?: "N/A"
            BookingSuccessScreen(
                onNavigateToHome = { navController.navigate(Screen.Home.route) { popUpTo(0) } },
                movieTitle = movieTitle,
                bookedSeats = seats,
                theater = theater,
                totalPrice = price,
                timing = timing
            )
        }

        composable(route = Screen.BookedTickets.route) { BookedTicketsScreen(onPopBackStack = { navController.popBackStack() }) }
    }
}
