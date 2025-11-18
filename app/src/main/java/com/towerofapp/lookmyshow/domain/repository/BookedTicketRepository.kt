package com.towerofapp.lookmyshow.domain.repository

import com.towerofapp.lookmyshow.domain.model.BookedTicket

interface BookedTicketRepository {
    suspend fun saveBookedTicket(ticket: BookedTicket)
    suspend fun getBookedTickets(): List<BookedTicket>
    suspend fun deleteAllBookedTicket()
}