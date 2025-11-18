package com.towerofapp.lookmyshow.domain.repository

import com.towerofapp.lookmyshow.domain.model.Movie

interface MovieRepository {
    suspend fun getTodaysMovies(): Result<List<Movie>>
}