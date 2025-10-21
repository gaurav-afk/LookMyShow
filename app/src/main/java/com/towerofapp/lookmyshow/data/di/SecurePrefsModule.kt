package com.towerofapp.lookmyshow.data.di

import android.content.Context
import com.towerofapp.lookmyshow.core.SecurePrefsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SecurePrefsModule {

    @Provides
    @Singleton
    fun provideSecurePrefs(@ApplicationContext context: Context): SecurePrefsManager {
        return SecurePrefsManager.getInstance(context)
    }
}