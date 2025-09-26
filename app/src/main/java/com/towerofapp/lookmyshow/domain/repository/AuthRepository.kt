package com.towerofapp.lookmyshow.domain.repository

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
    fun isUserLoggedIn(): Boolean
    fun logout()
}
