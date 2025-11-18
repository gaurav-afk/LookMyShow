package com.towerofapp.lookmyshow.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "city") val city: String,
    @Json(name = "date") val date: String,
    @Json(name = "movies") val movies: List<Movie>
)

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "language") val language: String,
    @Json(name = "genre") val genre: List<String>,
    @Json(name = "duration_mins") val durationMins: Int,
    @Json(name = "poster_url") val posterUrl: String,
    @Json(name = "theaters") val theaters: List<Theater>,
    @Json(name = "votes") val votes: Int,
    @Json(name = "rating") val rating: Float
)

@JsonClass(generateAdapter = true)
data class Theater(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "address") val address: String,
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double,
    @Json(name = "showtimes") val showtimes: List<String>
)
