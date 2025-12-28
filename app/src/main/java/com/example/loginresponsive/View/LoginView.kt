package com.example.loginresponsive.View

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginresponsive.Model.SignUpUiState
import com.example.loginresponsive.R
import com.example.loginresponsive.Routes
import com.example.loginresponsive.ViewModel.SignUpViewModel

@Composable
fun LoginView(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
    signUpViewModel: SignUpViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by signUpViewModel.uiState.observeAsState(SignUpUiState())

    val loginHabilitado = uiState.email.isNotEmpty() && uiState.password.isNotEmpty()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val widthModifier = when(windowSize) {
            WindowWidthSizeClass.Compact -> Modifier.fillMaxWidth(0.9f)
            WindowWidthSizeClass.Medium -> Modifier.fillMaxWidth(0.7f)
            else -> Modifier.width(400.dp)
        }

        Card(modifier = widthModifier.padding(16.dp)) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.person),
                        contentDescription = "Logo",
                        tint = Color.Blue,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Institut App", style = MaterialTheme.typography.headlineSmall)
                }

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { signUpViewModel.onEmailChange(it) },
                    label = { Text("Email") },
                    leadingIcon = { Icon(painterResource(R.drawable.mail), null) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { signUpViewModel.onPasswordChange(it) },
                    label = { Text("Password") },
                    leadingIcon = { Icon(painterResource(R.drawable.pass), null) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        Toast.makeText(context, "Login correcte!", Toast.LENGTH_SHORT).show()
                    },
                    enabled = loginHabilitado,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entrar")
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = { navController.navigate(Routes.SignUp.route) }) {
                    Text("No tens compte? Registra't")
                }
            }
        }
    }
}