package com.towerofapp.lookmyshow.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.towerofapp.lookmyshow.domain.model.Movie
import com.towerofapp.lookmyshow.ui.components.MovieItem
import com.towerofapp.lookmyshow.ui.navigation.Screen
import com.towerofapp.lookmyshow.ui.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(navController: NavController, viewModel: MoviesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is MoviesViewModel.MoviesUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is MoviesViewModel.MoviesUiState.Success -> {
                if (state.movies.isEmpty()) {
                    Text("No movies showing today.", modifier = Modifier.align(Alignment.Center))
                } else {
                    MovieList(
                        movies = state.movies,
                        navController = navController
                    )
                }
            }
            is MoviesViewModel.MoviesUiState.Error -> {
                Text(
                    text = "Error: ${state.message}",
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun MovieList(
    movies: List<Movie>,
    navController: NavController
) {
    Column {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(42.dp)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = Color.White,
                text = "Now showing: ${movies.size} movies"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 30.dp, bottom = 120.dp),
                modifier = Modifier.wrapContentHeight()
            ) {
                items(movies, key = { it.id }) { movie ->
                    MovieItem(
                        movie = movie,
                        onClick = {
                            navController.navigate(Screen.Theatres.createRoute(movie.id))
                        }
                    )
                }
            }
        }


    }

}
