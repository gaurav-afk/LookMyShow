package com.towerofapp.lookmyshow.ui.view.home

import android.text.Layout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.towerofapp.lookmyshow.ui.components.HallLayoutView
import com.towerofapp.lookmyshow.ui.components.HallScreen
import com.towerofapp.lookmyshow.ui.components.model.HallLayout
import com.towerofapp.lookmyshow.ui.components.model.Seat
import com.towerofapp.lookmyshow.ui.components.model.SeatStatus
import com.towerofapp.lookmyshow.ui.components.model.SeatType
import java.net.URLDecoder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HallLayoutScreen(
    navController: NavController,
    movieId: String,
    timeSlot: String,
    movieTitle: String
) {

    // Decode timeSlot if you URL-encoded it
    val decodedTimeSlot = timeSlot.replace("-", ":")

    // Sample hall layout (replace with API call later)
    val hallLayout = remember {
        HallLayout(
            rows = listOf("A", "B", "C", "D", "E"),
            seats = mapOf(
                "A" to List(8) { Seat("A", it + 1, SeatType.REGULAR, SeatStatus.AVAILABLE) },
                "B" to List(10) { Seat("B", it + 1, SeatType.PREMIUM, SeatStatus.AVAILABLE) },
                "C" to List(12) { Seat("C", it + 1, SeatType.REGULAR, SeatStatus.BOOKED) },
                "D" to List(10) { Seat("D", it + 1, SeatType.REGULAR, SeatStatus.AVAILABLE) },
                "E" to List(6) { Seat("E", it + 1, SeatType.PREMIUM, SeatStatus.AVAILABLE) },
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Seats") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                text = "Movie: $movieTitle",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Time: $decodedTimeSlot",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )



            HallLayoutView(
                hallLayout = hallLayout,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) { selectedSeat ->
                // Handle seat selection locally (for now just print)
                println("Selected seat: ${selectedSeat.row}${selectedSeat.number}")
            }

            Spacer(modifier = Modifier.height(12.dp))

            HallScreen()
        }
    }
}
