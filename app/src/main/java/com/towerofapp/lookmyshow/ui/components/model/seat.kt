package com.towerofapp.lookmyshow.ui.components.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class SeatType { REGULAR, PREMIUM, DISABLED }
enum class SeatStatus { AVAILABLE, BOOKED, SELECTED }


data class Seat(
    val row: String,
    val number: Int,
    val type: SeatType,
    val initialStatus: SeatStatus
) {
    var status by mutableStateOf(initialStatus)
}
