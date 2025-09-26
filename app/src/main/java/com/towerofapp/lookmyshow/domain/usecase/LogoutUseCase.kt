package com.towerofapp.lookmyshow.domain.usecase

import com.towerofapp.lookmyshow.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.logout()
}
