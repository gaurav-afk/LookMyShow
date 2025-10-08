package com.towerofapp.lookmyshow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.towerofapp.lookmyshow.ui.components.model.HallLayout
import com.towerofapp.lookmyshow.ui.components.model.Seat
import com.towerofapp.lookmyshow.ui.components.model.SeatStatus
import com.towerofapp.lookmyshow.ui.components.model.SeatType

@Composable
fun HallLayoutView(
    modifier: Modifier,
    hallLayout: HallLayout,
    onSeatSelected: (Seat) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        hallLayout.rows.forEach { row ->
            Row {
                hallLayout.seats[row]?.forEach { seat ->
                    SeatView(seat = seat) {
                        onSeatSelected(it)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun SeatView(seat: Seat, onClick: (Seat) -> Unit) {
    val color = when (seat.status) {
        SeatStatus.AVAILABLE -> Color.Gray
        SeatStatus.BOOKED -> Color.Red
        SeatStatus.SELECTED -> Color.Blue
    }

    Box(
        modifier = Modifier
            .size(32.dp)
            .padding(2.dp)
            .background(color,
                shape = RoundedCornerShape(2.dp))
            .clickable {
                if (seat.status == SeatStatus.AVAILABLE) {
                    seat.status = SeatStatus.SELECTED
                    onClick(seat)
                } else if (seat.status == SeatStatus.SELECTED) {
                    seat.status = SeatStatus.AVAILABLE
                    onClick(seat)
                }
            }
    )
}
