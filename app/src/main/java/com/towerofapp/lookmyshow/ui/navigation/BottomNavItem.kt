package com.towerofapp.lookmyshow.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Movies : BottomNavItem("movies_screen", Icons.Filled.Movie, "Movies")
    object Find : BottomNavItem("find_screen", Icons.Filled.Search, "Find")
    object Profile : BottomNavItem("profile_screen", Icons.Filled.AccountCircle, "Profile")
}