package com.towerofapp.lookmyshow.ui.view.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.towerofapp.lookmyshow.data.model.Movie
import com.towerofapp.lookmyshow.ui.viewmodel.MoviesUiState
import com.towerofapp.lookmyshow.ui.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(viewModel: MoviesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is MoviesUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is MoviesUiState.Success -> {
                if (state.movies.isEmpty()) {
                    Text("No movies showing today.", modifier = Modifier.align(Alignment.Center))
                } else {
                    MovieList(movies = state.movies)
                }
            }
            is MoviesUiState.Error -> {
                Text(
                    text = "Error: ${state.message}",
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies, key = { it.id }) { movie ->
            MovieItem(movie = movie)
        }
    }
}