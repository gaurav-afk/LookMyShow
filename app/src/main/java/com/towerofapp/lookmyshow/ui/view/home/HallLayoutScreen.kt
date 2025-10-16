package com.towerofapp.lookmyshow.ui.view.home

import android.R.attr.navigationIcon
import android.R.attr.padding
import android.R.attr.text
import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.towerofapp.lookmyshow.ui.components.HallLayoutView
import com.towerofapp.lookmyshow.ui.components.HallScreen
import com.towerofapp.lookmyshow.ui.components.model.HallLayout
import com.towerofapp.lookmyshow.ui.components.model.Seat
import com.towerofapp.lookmyshow.ui.components.model.SeatStatus
import com.towerofapp.lookmyshow.ui.components.model.SeatType
import org.intellij.lang.annotations.JdkConstants
import java.net.URLDecoder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HallLayoutScreen(
    navController: NavController,
    movieId: String,
    timeSlot: String,
    movieTitle: String
) {

    var selectedSeats by remember { mutableStateOf(setOf<String>()) }
    val decodedTimeSlot = timeSlot.replace("-", ":")

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
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){

                        Text("Select Seats ")
                        if (selectedSeats.size>0){
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(color = Color.Red, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${selectedSeats.size}",
                                    fontSize = 12.sp,
                                    color = Color.White,
                                )
                            }
                        }
                    }
                    },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (selectedSeats.isNotEmpty()) {
                BottomAppBar(
                    modifier = Modifier
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable{
                                navController.navigate("booking/$movieTitle/${selectedSeats.joinToString(",")}")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .background(color = Color.Red, shape = RoundedCornerShape(8.dp))
                                .padding(vertical = 12.dp)
                        ) {
                            Text(
                                text = "Pay ${550 * selectedSeats.size}",
                                color = Color.White
                            )
                        }
                    }
                }
            }
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
            ) { seat ->
                var seatNumber = seat.row+seat.number.toString()
                if (seatNumber in selectedSeats){
                    selectedSeats -= seatNumber
                    seat.status = SeatStatus.AVAILABLE
                } else{
                    selectedSeats += seatNumber
                    seat.status = SeatStatus.SELECTED
                }
                println("Selected seat: ${selectedSeats.joinToString(",")}")
            }

            Spacer(modifier = Modifier.height(12.dp))
            HallScreen()
        }
    }
}