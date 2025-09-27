package com.towerofapp.lookmyshow.ui.view.home


import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.towerofapp.lookmyshow.ui.navigation.BottomNavItem
import com.towerofapp.lookmyshow.ui.theme.LookMyShowTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(appNavController: NavHostController) {
    val bottomNavHostController = rememberNavController()
    val items = listOf(
        BottomNavItem.Movies,
        BottomNavItem.Find,
        BottomNavItem.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by bottomNavHostController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            bottomNavHostController.navigate(screen.route) {
                                popUpTo(bottomNavHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        HomeNavGraph(
            bottomNavHostController = bottomNavHostController,
            appNavController = appNavController,
        )
    }
}

@Composable
fun HomeNavGraph(
    bottomNavHostController: NavHostController,
    appNavController: NavHostController
) {
    NavHost(
        navController = bottomNavHostController,
        startDestination = BottomNavItem.Movies.route,
    ) {
        composable(BottomNavItem.Movies.route) {
            MoviesScreen()
        }
        composable(BottomNavItem.Find.route) { FindScreen() }
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
