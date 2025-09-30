package com.towerofapp.lookmyshow.ui.view.home


import android.R.attr.onClick
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.towerofapp.lookmyshow.data.remote.MockTheatreDataSource
import com.towerofapp.lookmyshow.ui.components.GlassNavigationBar
import com.towerofapp.lookmyshow.ui.navigation.BottomNavItem
import com.towerofapp.lookmyshow.ui.theme.LookMyShowTheme
import com.towerofapp.lookmyshow.ui.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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
                appNavController = appNavController,
                moviesViewModel = viewModel
            )
        }
}

@Composable
fun HomeNavGraph(
    bottomNavHostController: NavHostController,
    appNavController: NavHostController,
    moviesViewModel: MoviesViewModel,
) {
    NavHost(
        navController = bottomNavHostController,
        startDestination = BottomNavItem.Movies.route,
    ) {
        composable(BottomNavItem.Movies.route) {
            MoviesScreen()
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
