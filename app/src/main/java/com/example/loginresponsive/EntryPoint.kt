package com.example.loginresponsive

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginresponsive.View.LoginView
import com.example.loginresponsive.View.SignUpView

@Composable
fun EntryPoint(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginView(
                navController = navController
            )
        }
        composable(Routes.SignUp.route) {
            SignUpView(
                navController = navController
            )
        }
    }
}