package com.towerofapp.lookmyshow.data.di

import com.towerofapp.lookmyshow.data.repository.AuthRepositoryImpl
import com.towerofapp.lookmyshow.data.repository.MovieRepositoryImpl
import com.towerofapp.lookmyshow.domain.repository.AuthRepository
import com.towerofapp.lookmyshow.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

}