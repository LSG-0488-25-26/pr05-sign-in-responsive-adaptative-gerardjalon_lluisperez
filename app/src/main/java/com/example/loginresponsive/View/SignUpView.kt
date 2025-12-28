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
        WindowWidthSizeClass.Compact -> {
            SignUpCompact(navController, signUpViewModel, uiState)
        }
        WindowWidthSizeClass.Medium -> {
            SignUpMedium(navController, signUpViewModel, uiState)
        }
        WindowWidthSizeClass.Expanded -> {
            SignUpExpanded(navController, signUpViewModel, uiState)
        }
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (!isLandscape) {
            BannerSuperior(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .weight(1f)
                .padding(bottom = 16.dp)
        ) {
            ContenidoFormulario(navController, viewModel, uiState)
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
        BannerSuperior(Modifier.fillMaxWidth().height(100.dp))
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.9f)
        ) {
            ContenidoFormulario(navController, viewModel, uiState)
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
                .background(color = Color.Blue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.person),
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "Gimnasio App",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
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
                    .width(400.dp)
                    .fillMaxHeight(0.9f)
            ) {
                ContenidoFormulario(navController, viewModel, uiState)
            }
        }
    }
}

@Composable
fun BannerSuperior(modifier: Modifier) {
    Row(
        modifier = modifier.background(color = Color.Blue),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.person),
            contentDescription = "Logo",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Gimnasio App",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContenidoFormulario(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel,
    uiState: SignUpUiState
) {
    val context = LocalContext.current

    val isEmailValid = uiState.email.contains("@")
    val isPhoneValid = uiState.telefon.all { it.isDigit() } && uiState.telefon.isNotEmpty()
    val isPassMatch = uiState.password == uiState.confirmPassword && uiState.password.isNotEmpty()
    val isFormValid = isEmailValid && isPhoneValid && isPassMatch && uiState.termesAcceptats && uiState.nomSencer.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.nomSencer,
            onValueChange = { signUpViewModel.onNomSencerChange(it) },
            label = { Text(text = "Nom Sencer") },
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
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.email.isNotEmpty() && !isEmailValid
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.telefon,
            onValueChange = { signUpViewModel.onTelefonChange(it) },
            label = { Text(text = "Telèfon") },
            leadingIcon = { Icon(painterResource(R.drawable.phone), contentDescription = "Telèfon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.telefon.isNotEmpty() && !isPhoneValid
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
            isError = uiState.confirmPassword.isNotEmpty() && !isPassMatch
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
            enabled = isFormValid
        ) {
            Text(text = "Sign Up")
        }
    }
}