package com.towerofapp.lookmyshow.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TheaterListResponse(
    val city: String,
    val date: String,
    val theaters: List<Theaters>
)

@JsonClass(generateAdapter = true)
data class Theaters(
    val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val movies: List<TheaterMovie>
)

@JsonClass(generateAdapter = true)
data class TheaterMovie(
    val id: String,
    val title: String,
    val showtimes: List<String>
)
