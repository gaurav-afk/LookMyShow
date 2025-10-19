package com.towerofapp.lookmyshow.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.towerofapp.lookmyshow.data.local.dao.BookedTicketDao
import com.towerofapp.lookmyshow.data.local.entity.BookedTicketEntity


@Database(
    entities = [BookedTicketEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookedTicketDao(): BookedTicketDao
}

