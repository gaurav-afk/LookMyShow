package com.towerofapp.lookmyshow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.lookmyshow.data.model.Movie
import com.towerofapp.lookmyshow.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            _uiState.value = MoviesUiState.Loading
            try {
                val result = movieRepository.getTodaysMovies()
                result.onSuccess { movies ->
                    _uiState.value = MoviesUiState.Success(movies)
                }.onFailure { e ->
                    _uiState.value = MoviesUiState.Error(e.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                _uiState.value = MoviesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    sealed class MoviesUiState {
        object Loading : MoviesUiState()
        data class Success(val movies: List<Movie>) : MoviesUiState()
        data class Error(val message: String) : MoviesUiState()
    }
}
