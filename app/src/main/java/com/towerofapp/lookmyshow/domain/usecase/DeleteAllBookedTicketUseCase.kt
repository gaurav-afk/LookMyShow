package com.towerofapp.lookmyshow.domain.usecase

import com.towerofapp.lookmyshow.domain.repository.BookedTicketRepository
import javax.inject.Inject

class DeleteAllBookedTicketUseCase @Inject constructor(private val repository: BookedTicketRepository){
    suspend operator fun invoke() = repository.deleteAllBookedTicket()
}