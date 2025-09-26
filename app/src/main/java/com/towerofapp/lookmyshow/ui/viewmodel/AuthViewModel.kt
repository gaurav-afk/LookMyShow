package com.towerofapp.lookmyshow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
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

    fun login(email: String, password: String) = viewModelScope.launch {
        _authState.value = AuthState.Loading
        _authState.value = try {
            loginUseCase(email, password)
            AuthState.Success
        } catch (e: Exception) {
            AuthState.Error(e.message)
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


//// AuthViewModel.kt
//package com.towerofapp.lookmyshow.ui.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.towerofapp.lookmyshow.data.repository.AuthRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class AuthViewModel @Inject constructor(
//    private val repository: AuthRepository
//): ViewModel() {
//
//    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
//    val authState = _authState.asStateFlow()
//
//    fun signUp(email: String, password: String) = viewModelScope.launch {
//        _authState.value = AuthState.Loading
//        repository.signUp(email, password) { success, message ->
//            _authState.value = if (success) AuthState.Success else AuthState.Error(message)
//        }
//    }
//
//    fun login(email: String, password: String) = viewModelScope.launch {
//        _authState.value = AuthState.Loading
//        repository.login(email, password) { success, message ->
//            _authState.value = if (success) AuthState.Success else AuthState.Error(message)
//        }
//    }
//
//    fun logout() {
//        repository.logout()
//        _authState.value = AuthState.Idle
//    }
//}
//
//sealed class AuthState {
//    object Idle : AuthState()
//    object Loading : AuthState()
//    object Success : AuthState()
//    data class Error(val message: String?) : AuthState()
//}