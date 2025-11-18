package com.towerofapp.lookmyshow.data.repository

import com.towerofapp.lookmyshow.domain.model.Movie
import com.towerofapp.lookmyshow.data.remote.MockMovieDataSource
import com.towerofapp.lookmyshow.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val mockMovieDataSource: MockMovieDataSource
) : MovieRepository {
    override suspend fun getTodaysMovies(): Result<List<Movie>> {
        return try {
            val movieListResponse = mockMovieDataSource.getMockMovies()
            if (movieListResponse != null) {
                Result.success(movieListResponse.movies)
            } else {
                Result.failure(Exception("Failed to parse mock movie data"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}