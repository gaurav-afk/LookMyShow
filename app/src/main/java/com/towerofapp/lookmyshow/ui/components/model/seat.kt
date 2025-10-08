package com.towerofapp.lookmyshow.ui.components.model

enum class SeatType { REGULAR, PREMIUM, DISABLED }
enum class SeatStatus { AVAILABLE, BOOKED, SELECTED }

data class Seat(
    val row: String,
    val number: Int,
    val type: SeatType,
    var status: SeatStatus
)
