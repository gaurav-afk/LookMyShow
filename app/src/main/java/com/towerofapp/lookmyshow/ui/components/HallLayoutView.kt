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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.towerofapp.lookmyshow.ui.components.model.HallLayout
import com.towerofapp.lookmyshow.ui.components.model.Seat
import com.towerofapp.lookmyshow.ui.components.model.SeatStatus
import com.towerofapp.lookmyshow.ui.components.model.SeatType

@Composable
fun HallLayoutView(
    modifier: Modifier = Modifier,
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
        SeatStatus.AVAILABLE -> Color.LightGray
        SeatStatus.BOOKED -> Color.Gray
        SeatStatus.SELECTED -> Color.Green
    }

    Box(
        modifier = Modifier
            .size(32.dp)
            .padding(2.dp)
            .background(
                color,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                if (seat.status == SeatStatus.AVAILABLE || seat.status == SeatStatus.SELECTED) {
                    seat.status = SeatStatus.SELECTED
                    onClick(seat)
                }
            } ,
        contentAlignment = Alignment.Center
    ){
        Text(
            text="${seat.row}${seat.number}",
            fontSize = 10.sp)
    }
}
