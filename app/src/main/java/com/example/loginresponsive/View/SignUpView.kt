package com.example.loginresponsive.View

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginresponsive.R
import com.example.loginresponsive.ViewModel.SignUpViewModel
import com.example.loginresponsive.Model.SignUpUiState
import com.example.loginresponsive.Routes

@Composable
fun SignUpView(navController: NavHostController, signUpViewModel: SignUpViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState by signUpViewModel.uiState.observeAsState(SignUpUiState())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .background(color = Color.Blue),
            verticalAlignment = Alignment.CenterVertically
        ) {
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.9f)){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Sign Up", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = uiState.nomSencer,
                    onValueChange = { signUpViewModel.onNomSencerChange(it) },
                    label = { Text(text = "Nom Sencer")},
                    leadingIcon = { Icon(painterResource(R.drawable.person), contentDescription = "Nom Sencer") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.dataNaixement,
                    onValueChange = { signUpViewModel.onDataNaixementChange(it) },
                    label = { Text(text = "Data naixement (DD/MM/AAAA)") },
                    leadingIcon = { Icon(painterResource(R.drawable.calendar), contentDescription = "Data Naixement") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { signUpViewModel.onEmailChange(it) },
                    label = { Text(text = "Email") },
                    leadingIcon = { Icon(painterResource(R.drawable.mail), contentDescription = "Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.telefon,
                    onValueChange = { signUpViewModel.onTelefonChange(it) },
                    label = { Text(text = "Telèfon") },
                    leadingIcon = { Icon(painterResource(R.drawable.phone), contentDescription = "Telèfon") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.nomUsuari,
                    onValueChange = { signUpViewModel.onNomUsuariChange(it) },
                    label = { Text(text = "Nom d'usuari") },
                    leadingIcon = { Icon(painterResource(R.drawable.face), contentDescription = "Nom d'usuari") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { signUpViewModel.onPasswordChange(it) },
                    label = { Text(text = "Password") },
                    leadingIcon = { Icon(painterResource(R.drawable.pass), contentDescription = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.confirmPassword,
                    onValueChange = { signUpViewModel.onConfirmPasswordChange(it) },
                    label = { Text(text = "Confirmar Password") },
                    leadingIcon = { Icon(painterResource(R.drawable.pass), contentDescription = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.password != uiState.confirmPassword
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = uiState.termesAcceptats,
                        onCheckedChange = { signUpViewModel.onTermesAcceptatsChange(it) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Accepto els termes i condicions del servei.")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        Toast.makeText(context, "Compte creat!", Toast.LENGTH_SHORT).show()
                        navController.navigate(Routes.Login.route)
                    },
                    enabled = uiState.termesAcceptats && uiState.password == uiState.confirmPassword && uiState.password.isNotEmpty()
                ) {
                    Text(text = "Sign Up")
                }
            }
        }
    }
}