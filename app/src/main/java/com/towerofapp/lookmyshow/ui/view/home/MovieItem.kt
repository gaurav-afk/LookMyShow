package com.towerofapp.lookmyshow.ui.view.home // Or your relevant package

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.towerofapp.lookmyshow.data.model.Movie

@Composable
fun MovieItem(movie: Movie) {
    Card(modifier = Modifier.padding(bottom = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${movie.title}")
            Text(text = "Director: ${movie.durationMins}")
        }
    }
}
