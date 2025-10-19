package com.towerofapp.lookmyshow.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.lookmyshow.data.model.BookedTicket
import com.towerofapp.lookmyshow.domain.usecase.DeleteAllBookedTicketUseCase
import com.towerofapp.lookmyshow.domain.usecase.GetBookedTicketsUseCase
import com.towerofapp.lookmyshow.domain.usecase.SaveBookedTicketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookedTicketViewModel @Inject constructor(
    private val saveBookedTicketUseCase: SaveBookedTicketUseCase,
    private val getBookedTicketsUseCase: GetBookedTicketsUseCase,
    private val deleteAllBookedTicketUseCase: DeleteAllBookedTicketUseCase
) : ViewModel() {

    private val _bookedTickets = MutableStateFlow<List<BookedTicket>>(emptyList())
    val bookedTickets = _bookedTickets.asStateFlow()

    fun saveBookedTicket(ticket: BookedTicket) {
        viewModelScope.launch {
            try {
                saveBookedTicketUseCase(ticket)
            } catch (e: Exception) {
                Log.e("BookedTicketViewModel", "Error saving booked ticket", e)
            }
        }
    }

    fun loadBookedTickets() {
        viewModelScope.launch {
            try {
                val tickets = getBookedTicketsUseCase()
                _bookedTickets.value = tickets
            } catch (e: Exception) {
                Log.e("BookedTicketViewModel", "Error getting booked tickets", e)
            }
        }
    }

    fun deleteAllBookedTickets() {
        viewModelScope.launch {
            try {
                deleteAllBookedTicketUseCase()
                _bookedTickets.value = emptyList()
            } catch (e: Exception) {
                Log.e("BookedTicketViewModel", "Error deleting booked tickets", e)
            }
        }
    }
}