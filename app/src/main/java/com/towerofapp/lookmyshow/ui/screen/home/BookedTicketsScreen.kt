package com.towerofapp.lookmyshow.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.towerofapp.lookmyshow.data.model.BookedTicket
import com.towerofapp.lookmyshow.ui.viewmodel.BookedTicketViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookedTicketsScreen(
    navController: NavController,
    viewModel: BookedTicketViewModel = hiltViewModel()
) {
    val tickets by viewModel.bookedTickets.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBookedTickets()
    }

    Column {
        TopAppBar(
            title = { Text("Booked Tickets", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
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
            .padding(horizontal = 16.dp)
        )
        {
            if (tickets.isEmpty()) {
                // No Tickets
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No tickets booked yet",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                }
            } else {
                // List of tickets
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(top = 20.dp, bottom = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(tickets) { ticket ->
                        TicketCard(ticket)
                    }
                }
            }
        }

    }
}

@Composable
fun TicketCard(ticket: BookedTicket) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = ticket.movieTitle, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Time: ${ticket.timing}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Theater: ${ticket.theater}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Seat: ${ticket.bookedSeats}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
