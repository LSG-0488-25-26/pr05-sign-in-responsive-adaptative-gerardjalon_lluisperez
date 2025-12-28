package com.example.loginresponsive.View

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginresponsive.R
import com.example.loginresponsive.ViewModel.SignUpViewModel
import com.example.loginresponsive.Model.SignUpUiState
import com.example.loginresponsive.Routes

@Composable
fun SignUpView(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
    signUpViewModel: SignUpViewModel = viewModel()
) {
    val uiState by signUpViewModel.uiState.observeAsState(SignUpUiState())

    when (windowSize) {
        WindowWidthSizeClass.Compact -> SignUpCompact(navController, signUpViewModel, uiState)
        WindowWidthSizeClass.Medium -> SignUpMedium(navController, signUpViewModel, uiState)
        WindowWidthSizeClass.Expanded -> SignUpExpanded(navController, signUpViewModel, uiState)
        else -> SignUpCompact(navController, signUpViewModel, uiState)
    }
}

@Composable
fun SignUpCompact(
    navController: NavHostController,
    viewModel: SignUpViewModel,
    uiState: SignUpUiState
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isLandscape) {
            BannerInstitut(
                Modifier.fillMaxWidth().fillMaxHeight(0.15f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .weight(1f)
                .padding(bottom = 10.dp)
        ) {
            FormularioContenido(navController, viewModel, uiState)
        }
    }
}

@Composable
fun SignUpMedium(
    navController: NavHostController,
    viewModel: SignUpViewModel,
    uiState: SignUpUiState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BannerInstitut(Modifier.fillMaxWidth().height(100.dp))
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.9f)
        ) {
            FormularioContenido(navController, viewModel, uiState)
        }
    }
}

@Composable
fun SignUpExpanded(
    navController: NavHostController,
    viewModel: SignUpViewModel,
    uiState: SignUpUiState
) {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.Blue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.person),
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Institut Tecnològic",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .width(450.dp)
                    .fillMaxHeight(0.9f)
            ) {
                FormularioContenido(navController, viewModel, uiState)
            }
        }
    }
}

@Composable
fun BannerInstitut(modifier: Modifier) {
    Row(
        modifier = modifier.background(color = Color.Blue),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.person),
            contentDescription = "Logo",
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Institut Tecnològic",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FormularioContenido(
    navController: NavHostController,
    viewModel: SignUpViewModel,
    uiState: SignUpUiState
) {
    val context = LocalContext.current

    val emailValido = uiState.email.contains("@")
    val telfValido = uiState.telefon.all { it.isDigit() } && uiState.telefon.length >= 9
    val passCoinciden = uiState.password.isNotEmpty() && uiState.password == uiState.confirmPassword
    val camposLlenos = uiState.nomSencer.isNotEmpty() && uiState.nomUsuari.isNotEmpty()

    val formOk = emailValido && telfValido && passCoinciden && camposLlenos && uiState.termesAcceptats

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Alta d'Usuari", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.nomSencer,
            onValueChange = { viewModel.onNomSencerChange(it) },
            label = { Text("Nom complet") },
            leadingIcon = { Icon(painterResource(R.drawable.person), null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.dataNaixement,
            onValueChange = { viewModel.onDataNaixementChange(it) },
            label = { Text("Data naixement (DD/MM/AAAA)") },
            leadingIcon = { Icon(painterResource(R.drawable.calendar), null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            leadingIcon = { Icon(painterResource(R.drawable.mail), null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.email.isNotEmpty() && !emailValido
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.telefon,
            onValueChange = { viewModel.onTelefonChange(it) },
            label = { Text("Telèfon") },
            leadingIcon = { Icon(painterResource(R.drawable.phone), null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.telefon.isNotEmpty() && !telfValido
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.nomUsuari,
            onValueChange = { viewModel.onNomUsuariChange(it) },
            label = { Text("Nom d'usuari") },
            leadingIcon = { Icon(painterResource(R.drawable.face), null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Password") },
            leadingIcon = { Icon(painterResource(R.drawable.pass), null) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordChange(it) },
            label = { Text("Confirmar Password") },
            leadingIcon = { Icon(painterResource(R.drawable.pass), null) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.confirmPassword.isNotEmpty() && !passCoinciden
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = uiState.termesAcceptats,
                onCheckedChange = { viewModel.onTermesAcceptatsChange(it) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Accepto els termes i condicions.")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Toast.makeText(context, "Dades enviades correctament", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.Login.route)
            },
            enabled = formOk,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}