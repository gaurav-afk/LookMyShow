package com.towerofapp.lookmyshow.ui.screen.home


import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.towerofapp.lookmyshow.ui.components.GlassNavigationBar
import com.towerofapp.lookmyshow.ui.navigation.BottomNavItem
import com.towerofapp.lookmyshow.ui.theme.LookMyShowTheme
import com.towerofapp.lookmyshow.ui.viewmodel.MoviesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(appNavController: NavHostController,viewModel: MoviesViewModel = hiltViewModel()) {
    val bottomNavHostController = rememberNavController()
    val items = listOf(
        BottomNavItem.Movies,
        BottomNavItem.Map,
        BottomNavItem.Profile
    )

        Scaffold(
            bottomBar = {
                GlassNavigationBar(items = items, navController = bottomNavHostController)
            }
        ) { innerPadding ->
            HomeNavGraph(
                bottomNavHostController = bottomNavHostController,
                appNavController = appNavController
            )
        }
}

@Composable
fun HomeNavGraph(
    bottomNavHostController: NavHostController,
    appNavController: NavHostController,
) {
    NavHost(
        navController = bottomNavHostController,
        startDestination = BottomNavItem.Movies.route,
    ) {
        composable(BottomNavItem.Movies.route) {
            MoviesScreen(navController = appNavController)
        }
        composable(BottomNavItem.Map.route) { MapScreen() }
        composable(BottomNavItem.Profile.route) { ProfileScreen(appNavController = appNavController) }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LookMyShowTheme {
        HomeScreen(rememberNavController())
    }
}
