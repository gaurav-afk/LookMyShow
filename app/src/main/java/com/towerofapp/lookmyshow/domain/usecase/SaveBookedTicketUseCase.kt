package com.towerofapp.lookmyshow.domain.usecase

import com.towerofapp.lookmyshow.domain.model.BookedTicket
import com.towerofapp.lookmyshow.domain.repository.BookedTicketRepository
import javax.inject.Inject

class SaveBookedTicketUseCase @Inject constructor(private val repository: BookedTicketRepository) {
    suspend operator fun invoke(ticket: BookedTicket) = repository.saveBookedTicket(ticket)
}