package com.towerofapp.lookmyshow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.lookmyshow.data.model.Theater
import com.towerofapp.lookmyshow.data.remote.MockMovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mockMovieDataSource: MockMovieDataSource
) : ViewModel() {

    private val _theaters = MutableStateFlow<List<Theater>>(emptyList())
    val theatersStateFlow: StateFlow<List<Theater>> = _theaters.asStateFlow()

    init {
        loadTheaters()
    }

    private fun loadTheaters() {
        viewModelScope.launch {
            val theaters = mockMovieDataSource.getMockMovies()?.movies?.flatMap { it.theaters } ?: emptyList()
            _theaters.value = theaters
        }
    }
}
