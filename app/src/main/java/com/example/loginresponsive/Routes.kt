package com.example.loginresponsive

sealed class Routes(val route: String) {
    object Login : Routes("LoginView")
    object SignUp : Routes("SignUpView")
}