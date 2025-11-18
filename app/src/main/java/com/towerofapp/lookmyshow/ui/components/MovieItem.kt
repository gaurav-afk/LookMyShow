package com.towerofapp.lookmyshow.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.towerofapp.lookmyshow.domain.model.Movie
import coil.compose.rememberAsyncImagePainter
import java.text.DecimalFormat

fun formatVotes(votes: Int): String {
    return when {
        votes >= 1_000_000 -> {
            val df = DecimalFormat("#.#")
            "${df.format(votes / 1_000_000.0)}M votes"
        }
        votes >= 1_000 -> {
            val df = DecimalFormat("#.#")
            "${df.format(votes / 1_000.0)}k votes"
        }
        else -> "$votes votes"
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .clickable{ onClick() }
                .width(175.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                Image(
                    painter = rememberAsyncImagePainter(model = movie.posterUrl),
                    contentDescription = "Movie poster",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.6f),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFbebebf))
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFff6410),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = movie.rating.toString(),
                            fontSize = 13.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        text = formatVotes(movie.votes),
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Title: ${movie.title}",
            color = Color.Black,
            style = TextStyle(
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .width(180.dp)
                .padding(start = 4.dp, end = 4.dp, bottom = 8.dp)
        )
    }
}
