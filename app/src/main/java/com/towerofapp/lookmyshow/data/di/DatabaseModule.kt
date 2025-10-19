package com.towerofapp.lookmyshow.data.di

import android.content.Context
import androidx.room.Room
import com.towerofapp.lookmyshow.data.local.dao.BookedTicketDao
import com.towerofapp.lookmyshow.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideBookedTicketDao(database: AppDatabase): BookedTicketDao {
        return database.bookedTicketDao()
    }
}