// In app/src/main/java/com/towerofapp/lookmyshow/ui/viewmodel/MoviesViewModel.kt
package com.towerofapp.lookmyshow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.lookmyshow.data.model.Movie // Make sure this import is correct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MoviesUiState {
    data object Loading : MoviesUiState
    data class Success(val movies: List<Movie>) : MoviesUiState
    data class Error(val message: String) : MoviesUiState
}

@HiltViewModel
class MoviesViewModel @Inject constructor(
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
                // TODO: Replace with actual data fetching logic
                val movies = emptyList<Movie>()
                _uiState.value = MoviesUiState.Success(movies)
            } catch (e: Exception) {
                _uiState.value = MoviesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}