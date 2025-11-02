package com.towerofapp.lookmyshow.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.towerofapp.lookmyshow.data.model.Theater
import com.towerofapp.lookmyshow.ui.viewmodel.MoviesViewModel
import com.towerofapp.lookmyshow.data.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheatersScreen(
    movieId: String,
    onPopBackStack: () -> Unit,
    onNavigateToHallLayout: (movieIdParam: String, safeTimeParam: String, encodedTitleParam: String, theatreNameParam: String) -> Unit,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Theaters", color = Color.White) },
            navigationIcon = {
                IconButton(onClick =  onPopBackStack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red)
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)) {
            when (val state = uiState) {
                is MoviesViewModel.MoviesUiState.Success -> {
                    val movie = state.movies.find { it.id == movieId }
                    if (movie != null) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "${movie.title}",
                                fontSize = 20.sp,
                                color = Color.Red,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(movie.theaters) { theatre ->
                                    TheatreItem(theatre = theatre, movie= movie, onNavigateToHallLayout = onNavigateToHallLayout)
                                }
                            }
                        }
                    } else {
                        Text(
                            "Movie not found",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                is MoviesViewModel.MoviesUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is MoviesViewModel.MoviesUiState.Error -> {
                    Text(
                        "Error: ${state.message}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun TheatreItem(theatre: Theater,
                onNavigateToHallLayout: (movieIdParam: String, safeTimeParam: String, encodedTitleParam: String, theatreNameParam: String) -> Unit,
                movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(12.dp)
    ) {

        Text(text = theatre.name, color = Color.Black, fontSize = 16.sp)
        Text(text = theatre.address, color = Color.DarkGray, fontSize = 12.sp)

        Spacer(modifier = Modifier.height(6.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            theatre.showtimes.forEach { time ->
                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.Red, shape = CircleShape)
                        .clickable {
                            val safeTime = time.replace(":", "-")
                            val encodedTitle = java.net.URLEncoder.encode(movie.title, "UTF-8")
                            onNavigateToHallLayout(movie.id, safeTime, encodedTitle, theatre.name)
                        }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = time, color = Color.Black, fontSize = 12.sp)
                }
            }
        }
    }
}
