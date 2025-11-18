package com.towerofapp.lookmyshow.data.repository

import com.towerofapp.lookmyshow.data.local.dao.BookedTicketDao
import com.towerofapp.lookmyshow.data.local.entity.toDomain
import com.towerofapp.lookmyshow.data.local.entity.toEntity
import com.towerofapp.lookmyshow.domain.model.BookedTicket
import com.towerofapp.lookmyshow.domain.repository.BookedTicketRepository
import javax.inject.Inject


class BookedTicketRepositoryImpl @Inject constructor(
    private val bookedTicketDao: BookedTicketDao
) : BookedTicketRepository {

    override suspend fun saveBookedTicket(ticket: BookedTicket) {
        val entity = ticket.toEntity()
        bookedTicketDao.insertBookedTicket(ticket = entity)
    }

    override suspend fun getBookedTickets(): List<BookedTicket> {
        val entities = bookedTicketDao.getAllBookedTickets()
        return entities.map { it.toDomain() }
    }

    override suspend fun deleteAllBookedTicket() {
        bookedTicketDao.deleteAllBookedTickets()
    }
}
