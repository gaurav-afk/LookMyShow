package com.towerofapp.lookmyshow.data.remote

import android.util.Log
import com.squareup.moshi.Moshi
import com.towerofapp.lookmyshow.data.model.MovieListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockMovieDataSource @Inject constructor(private val moshi: Moshi) {

    private val mockJsonResponse = """
    {
      "city": "Delhi-NCR",
      "date": "2025-09-27",
      "movies": [
        {
          "id": "mv001",
          "title": "Jolly LLB 3",
          "language": "Hindi",
          "genre": ["Comedy", "Drama"],
          "duration_mins": 145,
          "poster_url": "https://image.tmdb.org/t/p/w500/jolly_llb3.jpg",
          "theaters": [
            {
              "id": "th001",
              "name": "PVR Select City Walk",
              "address": "Select City Walk, Saket, Delhi",
              "latitude": 28.5246,
              "longitude": 77.2100,
              "showtimes": ["09:00", "10:30", "12:15", "13:45", "15:30", "17:00", "18:45", "20:15", "22:00", "23:30"]
            },
            {
              "id": "th002",
              "name": "Delite Cinema",
              "address": "Asaf Ali Road, Delhi",
              "latitude": 28.6488,
              "longitude": 77.2190,
              "showtimes": ["18:50", "21:50"]
            },
            {
              "id": "th003",
              "name": "Cinepolis Pacific NSP2",
              "address": "Nehru Place, Delhi",
              "latitude": 28.5470,
              "longitude": 77.2622,
              "showtimes": ["12:25", "15:00", "17:45", "20:05", "21:00", "21:30", "22:25", "23:25"]
            }
          ]
        },
        {
          "id": "mv002",
          "title": "Demon Slayer: Kimetsu no Yaiba – Infinity Castle",
          "language": "Japanese (with English subtitles)",
          "genre": ["Anime", "Action"],
          "duration_mins": 125,
          "poster_url": "https://image.tmdb.org/t/p/w500/demon_slayer_infinity_castle.jpg",
          "theaters": [
            {
              "id": "th001",
              "name": "PVR Select City Walk",
              "address": "Select City Walk, Saket, Delhi",
              "latitude": 28.5246,
              "longitude": 77.2100,
              "showtimes": ["09:05", "14:20", "20:15"]
            },
            {
              "id": "th003",
              "name": "Cinepolis Pacific NSP2",
              "address": "Nehru Place, Delhi",
              "latitude": 28.5470,
              "longitude": 77.2622,
              "showtimes": ["19:15"]
            },
            {
              "id": "th004",
              "name": "Cinepolis Janak Cinema",
              "address": "Janakpuri, Delhi",
              "latitude": 28.6093,
              "longitude": 77.0872,
              "showtimes": ["19:35"]
            },
            {
              "id": "th005",
              "name": "Ajay Devgn's NY Cinemas – Elan Epic Mall",
              "address": "Elan Epic Mall, Delhi",
              "latitude": 28.5771,
              "longitude": 77.3235,
              "showtimes": ["20:05"]
            }
          ]
        },
        {
          "id": "mv003",
          "title": "Chainsaw Man – The Movie: Reze Arc",
          "language": "Japanese (with English subtitles)",
          "genre": ["Anime", "Action"],
          "duration_mins": 130,
          "poster_url": "https://image.tmdb.org/t/p/w500/chainsaw_man_reze.jpg",
          "theaters": [
            {
              "id": "th001",
              "name": "PVR Select City Walk",
              "address": "Select City Walk, Saket, Delhi",
              "latitude": 28.5246,
              "longitude": 77.2100,
              "showtimes": ["09:40", "15:25", "21:10"]
            },
            {
              "id": "th003",
              "name": "Cinepolis Pacific NSP2",
              "address": "Nehru Place, Delhi",
              "latitude": 28.5470,
              "longitude": 77.2622,
              "showtimes": ["20:00", "22:20"]
            },
            {
              "id": "th004",
              "name": "Cinepolis Janak Cinema",
              "address": "Janakpuri, Delhi",
              "latitude": 28.6093,
              "longitude": 77.0872,
              "showtimes": ["19:35"]
            }
          ]
        }
      ]
    }
    """.trimIndent()

    fun getMockMovies(): MovieListResponse? {
        val adapter = moshi.adapter(MovieListResponse::class.java)
        Log.d("MockMovieDataSource", "Attempting to parse mock JSON") // Log
        return try {
            val parsed = adapter.fromJson(mockJsonResponse)
            Log.d("MockMovieDataSource", "Parsed movies count: ${parsed?.movies?.size}") // Log
            parsed
        } catch (e: Exception) {
            Log.e("MockMovieDataSource", "Error parsing JSON", e) // Log error
            null
        }
    }
}