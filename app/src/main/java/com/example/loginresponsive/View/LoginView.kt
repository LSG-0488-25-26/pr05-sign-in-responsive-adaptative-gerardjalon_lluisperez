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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.loginresponsive.ViewModel.SignUpViewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginresponsive.Model.SignUpUiState
import com.example.loginresponsive.R
import com.example.loginresponsive.Routes

@Composable
fun LoginView(navController: NavHostController, signUpViewModel: SignUpViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState by signUpViewModel.uiState.observeAsState(SignUpUiState())
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f).background(color = Color.Blue),
            verticalAlignment = Alignment.CenterVertically) {

        }

        Spacer(modifier = Modifier.height(48.dp))

        Card(modifier = Modifier.fillMaxSize(0.8f)){
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                Text(text = "Login")
                Spacer(modifier = Modifier.height(48.dp))
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { signUpViewModel.onEmailChange(it) },
                    label = { Text(text = "Email")},
                    leadingIcon = { Icon(painterResource(R.drawable.mail), contentDescription = "Email") }
                )
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { signUpViewModel.onPasswordChange(it) },
                    label = { Text(text = "Password")},
                    leadingIcon = { Icon(painterResource(R.drawable.pass), contentDescription = "Password") }
                )
                Spacer(modifier = Modifier.height(48.dp))
                Button(onClick = {
                    Toast.makeText(context, "Login correcte!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(24.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "¿No tens compte?")
                    TextButton(onClick = { navController.navigate(Routes.SignUp.route) }) {
                        Text(text = "Registrar-te")
                    }
                }
            }
        }
    }
}
