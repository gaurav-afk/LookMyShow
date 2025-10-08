package com.towerofapp.lookmyshow.ui.components.model

data class HallLayout(
    val rows: List<String>,
    val seats: Map<String, List<Seat>> // row -> list of seats
)