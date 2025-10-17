package com.towerofapp.lookmyshow.ui.view.auth

import android.R.attr.maxWidth
import android.R.attr.password
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
import com.towerofapp.lookmyshow.ui.viewmodel.MoviesViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by viewModel.authState.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
            .fillMaxSize()
            .background(color = Color(0xFFFFC00C0C)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            maxLines = 1,
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(viewModel = viewModel, email = email, password = password)
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("signup") }) {
            Text("Create account", color = Color.White)
        }

        when (state) {
            AuthViewModel.AuthState.Loading -> CircularProgressIndicator(color = Color.Red)
            is AuthViewModel.AuthState.Error -> Text("Error: ${(state as AuthViewModel.AuthState.Error).message}")
            AuthViewModel.AuthState.Success -> LaunchedEffect(state) {
                if (state is AuthViewModel.AuthState.Success) {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // remove login/signup from back stack
                    }
                }
            }

            else -> {}
        }
    }
}

@Composable
fun LoginButton(viewModel: AuthViewModel, email: String, password: String) {
    Button(
        onClick = { viewModel.login(email, password) },
        modifier = Modifier
            .width(280.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF790000),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text("Login")
    }
}