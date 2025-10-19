package com.towerofapp.lookmyshow.ui.view.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.towerofapp.lookmyshow.ui.components.model.Seat
import kotlin.collections.joinToString

@Composable
fun BookingSuccessScreen(
    navController: NavController,
    movieTitle: String,
    theater: String,
    bookedSeats: List<String>,
    price: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .padding(horizontal = 30.dp),

        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(100.dp)
            )

            Text(
                text = "Booking Successful!",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
            Text(
                text = "Movie: ${movieTitle}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
            Text(
                text = "Seats booked: ${bookedSeats.joinToString { "${it}" }}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
            Text(
                text = "Theater: ${theater}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
            Text(
                text = "Paid: ₹${price}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )

            Button(
                onClick = {navController.navigate("home")},
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Done")
            }
        }
    }
}