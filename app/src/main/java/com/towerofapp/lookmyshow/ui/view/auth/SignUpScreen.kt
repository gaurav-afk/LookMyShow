package com.towerofapp.lookmyshow.ui.view.auth

import com.towerofapp.lookmyshow.ui.viewmodel.AuthViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SignUpScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.authState.collectAsState()

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.signUp(email, password) }) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Already have an account?")
        }

        when(state) {
            AuthViewModel.AuthState.Loading -> Text("Loading...")
            is AuthViewModel.AuthState.Error -> Text("Error: ${(state as AuthViewModel.AuthState.Error).message}")
            AuthViewModel.AuthState.Success -> LaunchedEffect(Unit) { navController.navigate("home") }
            else -> {}
        }
    }
}
