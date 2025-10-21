package com.towerofapp.lookmyshow.data.model

data class BookedTicket(
    val id: Int = 0,
    val movieTitle: String,
    val theater: String,
    val bookedSeats: String,
    val timing: String,
    val price: String
)