package com.towerofapp.lookmyshow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.lookmyshow.core.SecurePrefsManager
import com.towerofapp.lookmyshow.domain.usecase.IsUserLoggedInUseCase
import com.towerofapp.lookmyshow.domain.usecase.LoginUseCase
import com.towerofapp.lookmyshow.domain.usecase.LogoutUseCase
import com.towerofapp.lookmyshow.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
    private val securePrefs: SecurePrefsManager
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    fun signUp(email: String, password: String) = viewModelScope.launch {
        _authState.value = AuthState.Loading
        _authState.value = try {
            signUpUseCase(email, password)
            AuthState.Success
        } catch (e: Exception) {
            AuthState.Error(e.message)
        }
    }

    fun saveUser(email: String) = securePrefs.saveEmail(email)

    fun getUser() = securePrefs.getEmail()

    fun login(email: String, password: String) = viewModelScope.launch {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email and password cannot be empty.")
            return@launch
        }
        _authState.value = AuthState.Loading
        try {
            val result = loginUseCase(email, password)
            if (result.isSuccess) {
                _authState.value = AuthState.Success
            } else {
                _authState.value = AuthState.Error(result.exceptionOrNull()?.message ?: "Login failed")
            }
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Unknown error")
        }
    }

    fun logout() {
        viewModelScope.launch {
        logoutUseCase()
        _authState.value = AuthState.Idle
        }
    }

    fun checkUser() {
        if (isUserLoggedInUseCase()) _authState.value = AuthState.Success
    }

     sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        object Success : AuthState()
        data class Error(val message: String?) : AuthState()
    }
}