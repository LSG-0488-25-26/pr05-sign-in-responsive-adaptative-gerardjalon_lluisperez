package com.example.loginresponsive

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginresponsive.View.LoginView
import com.example.loginresponsive.View.SignUpView

@Composable
fun EntryPoint(navController: NavHostController, windowSize: WindowWidthSizeClass) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginView(navController = navController, windowSize = windowSize)
        }
        composable(Routes.SignUp.route) {
            SignUpView(navController = navController, windowSize = windowSize)
        }
    }
}