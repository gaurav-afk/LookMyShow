package com.towerofapp.lookmyshow.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object Loading : Screen("loading")

    object Theatres : Screen("theatres/{movieId}") {
        fun createRoute(movieId: String) = "theatres/$movieId"
    }

    object Hall : Screen("hall/{movieId}/{timeSlot}/{movieTitle}/{theater}") {
        fun createRoute(movieId: String, timeSlot: String, movieTitle: String, theater: String) =
            "hall/$movieId/$timeSlot/${java.net.URLEncoder.encode(movieTitle, "UTF-8")}/$theater"
    }

    object Booking : Screen("booking/{movieTitle}/{selectedSeats}/{theater}/{timing}") {
        fun createRoute(movieTitle: String, seats: String, theater: String, timing: String) =
            "booking/$movieTitle/${seats}/$theater/$timing"
    }

    object Success : Screen("success/{movieTitle}/{selectedSeats}/{theater}/{price}/{timing}") {
        fun createRoute(movieTitle: String, seats: List<String>, theater: String, price: Double, timing: String) =
            "success/$movieTitle/${seats.joinToString(",")}/$theater/$price/$timing"
    }

    object BookedTickets : Screen("bookedTickets")
}
