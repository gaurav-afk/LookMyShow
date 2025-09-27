package com.towerofapp.lookmyshow.domain.repository

import com.towerofapp.lookmyshow.data.model.Movie

interface MovieRepository {
    suspend fun getTodaysMovies(): Result<List<Movie>>
}