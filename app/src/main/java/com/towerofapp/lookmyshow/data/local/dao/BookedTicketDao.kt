package com.towerofapp.lookmyshow.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.towerofapp.lookmyshow.data.local.entity.BookedTicketEntity

@Dao
interface BookedTicketDao {

    @Query("SELECT * FROM booked_tickets")
    suspend fun getAllBookedTickets(): List<BookedTicketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookedTicket(ticket: BookedTicketEntity)

    @Query("DELETE FROM booked_tickets")
    suspend fun deleteAllBookedTickets()
}