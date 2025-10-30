package com.towerofapp.lookmyshow.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import com.towerofapp.lookmyshow.ui.viewmodel.AuthViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SignUpScreen(onNavigateToHome: () -> Unit, onNavigateToLogin: ()->Unit, viewModel: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val state by viewModel.authState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checkUser()
    }

    LaunchedEffect(state) {
        when (state) {
            is AuthViewModel.AuthState.Success -> {
                viewModel.saveUser(email)
                onNavigateToHome()
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .pointerInput(Unit){
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
            .fillMaxSize()
            .background(color = Color(0xFFFFC00C0C)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(visualTransformation = PasswordVisualTransformation(),value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))
        SignUpButton(viewModel = viewModel, email = email, password = password)
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateToLogin
             ) {
            Text("Already have an account?", color = Color.White)
        }

        when (state) {
            AuthViewModel.AuthState.Loading  -> CircularProgressIndicator(color = Color.Red)
            is AuthViewModel.AuthState.Error -> Text("Error: ${(state as AuthViewModel.AuthState.Error).message}")
            AuthViewModel.AuthState.Success  -> LaunchedEffect(Unit) {
                if (state is AuthViewModel.AuthState.Success) {
                    onNavigateToHome
                }
            }
            else                             -> {}
        }
    }
}

@Composable
fun SignUpButton(viewModel: AuthViewModel, email: String, password: String) {
    Button(
        onClick = { viewModel.signUp(email, password) },
        modifier = Modifier
            .width(280.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF790000),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text("Sign Up")
    }
}