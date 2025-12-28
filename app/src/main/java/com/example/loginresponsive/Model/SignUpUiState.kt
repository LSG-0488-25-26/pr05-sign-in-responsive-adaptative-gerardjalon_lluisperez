package com.example.loginresponsive.Model

data class SignUpUiState(
    val nomSencer: String = "",
    val dataNaixement: String = "",
    val email: String = "",
    val telefon: String = "",
    val nomUsuari: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val termesAcceptats: Boolean = false,
)