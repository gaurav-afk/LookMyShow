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
  "date": "2025-10-07",
  "movies": [
    {
      "id": "mv001",
      "title": "Jolly LLB 3",
      "language": "Hindi",
      "genre": ["Comedy", "Drama"],
      "duration_mins": 145,
      "votes": 2450,
      "rating": 4.2,
      "poster_url": "https://imgs.search.brave.com/t9CrIuvJ-XThXRgodiUMjToMHt8gUs9MZB4B5-kbKRk/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9jZG4u/ZGlzdHJpY3QuaW4v/bW92aWVzLWFzc2V0/cy9pbWFnZXMvY2lu/ZW1hL0pvbGx5LUxM/Qi1wb3N0ZXItODI4/NjQyMDAtOWQxYi0x/MWYwLWIzNDItOGQz/ZGNlOTEzZjM4Lmpw/Zz9mb3JtYXQ9d2Vi/cCZpbXdpZHRoPTI1/Ng",
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
      "votes": 190020,
      "rating": 4.7,
      "poster_url": "https://imgs.search.brave.com/jdLemK_AWcx41qZzL91vIgxPVP7Gzq7Ho5GxWPiSOC4/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9hbWMt/dGhlYXRyZXMtcmVz/LmNsb3VkaW5hcnku/Y29tL2ltYWdlL3Vw/bG9hZC9jX2xpbWl0/LHdfMjcyL2ZfYXV0/by9xX2F1dG8vdjE3/NTg5MTQzMDgvYW1j/LWNkbi9wcm9kdWN0/aW9uLzIvbW92aWVz/Lzc3ODAwLzc3ODM2/L1Bvc3RlckR5bmFt/aWMvMTczMjg5LnBu/Zw",
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
      "votes": 980,
      "rating": 4.5,
      "poster_url": "https://imgs.search.brave.com/WqBufvLS9RkpLebpWFD6HaBWFSghoetS3r13GVluF5Y/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tLm1l/ZGlhLWFtYXpvbi5j/b20vaW1hZ2VzL00v/TVY1Qk4yRmhaamRp/TkRVdE9XTTROUzAw/Wm1aaExXRXhOemd0/TWpKbU5XSXpOekF6/TWpZM1hrRXlYa0Zx/Y0djQC5qcGc",
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
    },
    {
      "id": "mv004",
      "title": "Joker: Folie à Deux",
      "language": "English",
      "genre": ["Drama", "Thriller"],
      "duration_mins": 122,
      "votes": 120000,
      "rating": 4.8,
      "poster_url": "https://imgs.search.brave.com/WM7gXYOZeOnW_-Dxk-HR9pTSSrmjTYH6W9srUpq-nLE/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9mci53/ZWIuaW1nNS5hY3N0/YS5uZXQvY18zMTBf/NDIwL2ltZy9mNS9h/MS9mNWExMDEyYWNi/NzNiYjM4YjQ4MDFm/ZTM3MWE1NDIyNy5q/cGc",
      "theaters": [
        {
          "id": "th001",
          "name": "PVR Select City Walk",
          "address": "Select City Walk, Saket, Delhi",
          "latitude": 28.5246,
          "longitude": 77.2100,
          "showtimes": ["11:00", "14:30", "18:00", "21:30"]
        },
        {
          "id": "th002",
          "name": "Delite Cinema",
          "address": "Asaf Ali Road, Delhi",
          "latitude": 28.6488,
          "longitude": 77.2190,
          "showtimes": ["13:00", "16:30", "20:00"]
        }
      ]
    },
    {
      "id": "mv005",
      "title": "Tiki Taka",
      "language": "Malayalam",
      "genre": ["Comedy", "Romance"],
      "duration_mins": 135,
      "votes": 5000,
      "rating": 4.3,
      "poster_url": "https://imgs.search.brave.com/YamHozGT4mTkRFyAPiSMfkYJpPEm0bsjthFc3U5HEkE/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pMC53/cC5jb20vd3d3LnNv/Y2lhbG5ld3MueHl6/L3dwLWNvbnRlbnQv/dXBsb2Fkcy8yMDIz/LzAzLzEyL0lNRy0y/MDIzMDMxMi1XQTAw/MjYtMS5qcGc_dz0z/ODQmaD00ODEmcXVh/bGl0eT04MCZ6b29t/PTEmc3NsPTE",
      "theaters": [
        {
          "id": "th006",
          "name": "Sree Theatre",
          "address": "MG Road, Kochi",
          "latitude": 9.9815,
          "longitude": 76.2811,
          "showtimes": ["10:00", "13:30", "17:00", "20:30"]
        },
        {
          "id": "th007",
          "name": "Padma Theatre",
          "address": "Kochi, Kerala",
          "latitude": 9.9312,
          "longitude": 76.2673,
          "showtimes": ["11:15", "14:45", "18:15", "21:45"]
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