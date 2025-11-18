package com.towerofapp.lookmyshow.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.towerofapp.lookmyshow.domain.model.BookedTicket


@Entity(tableName = "booked_tickets")
data class BookedTicketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val movieTitle: String,
    val theater: String,
    val bookedSeats: String,
    val price: String,
    val timing: String
)

fun BookedTicket.toEntity(): BookedTicketEntity {
    return BookedTicketEntity(
        id = this.id,
        movieTitle = this.movieTitle,
        theater = this.theater,
        bookedSeats = this.bookedSeats,
        price = this.price,
        timing = this.timing
    )
}

fun BookedTicketEntity.toDomain(): BookedTicket {
    return BookedTicket(
        id = this.id,
        movieTitle = this.movieTitle,
        theater = this.theater,
        bookedSeats = this.bookedSeats,
        timing = this.timing,
        price = this.price
    )
}
