package com.towerofapp.lookmyshow.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.towerofapp.lookmyshow.core.AppConfig
import com.towerofapp.lookmyshow.data.model.BookedTicket
import com.towerofapp.lookmyshow.ui.viewmodel.BookedTicketViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    viewModel: BookedTicketViewModel = hiltViewModel(),
    navController: NavController,
    movieTitle: String,
    theater: String,
    seats: List<String>,
    timing: String
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Confirm Booking")
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
            BottomAppBar {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(color = Color.Red)
                            .padding(vertical = 8.dp)
                            .clickable {
                                coroutineScope.launch {
                                    viewModel.saveBookedTicket(BookedTicket(movieTitle = movieTitle, theater = theater, bookedSeats = seats.joinToString(separator = ","), price = (AppConfig.ticketPrice * seats.size).toString()))
                                }
                                navController.navigate("success/$movieTitle/${seats.joinToString(",")}/$theater/${AppConfig.ticketPrice * seats.size + AppConfig.convenienceFee}/$timing")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Continue",
                            color = Color.White
                        )
                    }
                }
            }
        },
    ) { padding ->
        TicketCard(padding, movieTitle=movieTitle, seats = seats)
    }
}



@Composable
fun TicketCard(padding: PaddingValues,
               movieTitle: String,
               seats: List<String>){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(3.dp, RoundedCornerShape(16.dp))
                .background(Color(0xFF373737))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // Movie Title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = movieTitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White)
                }

                Divider(
                    color = Color(0xFFFF3D3D),
                    thickness = 2.dp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                // Seat Info
                Text(
                    text = "Seats(${seats.size}): ${if (seats.isNotEmpty()) seats.joinToString(", ") else "—"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White)


                Spacer(modifier = Modifier.height(8.dp))

                // Pricing Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Ticket (₹${AppConfig.ticketPrice} × ${seats.size})", color = Color.White)
                    Text("₹${AppConfig.ticketPrice * seats.size}", color = Color.White)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Convenience Fee", color = Color.White)
                    Text("₹${if (seats.isNotEmpty()) AppConfig.convenienceFee else 0}", color = Color.White)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Total", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    Text(
                        text = "₹${AppConfig.ticketPrice * seats.size + AppConfig.convenienceFee}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Green
                    )
                }
            }

            // small cut shapes at each end
            Box(
                modifier = Modifier
                    .padding(top = 88.dp)
                    .offset(x = (-8).dp)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE0E0E0), CircleShape)
            )

            Box(
                modifier = Modifier
                    .padding(top = 88.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (8).dp)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE0E0E0), CircleShape)
            )
        }
    }
}