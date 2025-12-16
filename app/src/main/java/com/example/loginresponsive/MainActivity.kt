package com.example.loginresponsive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.loginresponsive.View.SignUpView
import com.example.loginresponsive.ui.theme.LoginResponsiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginResponsiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    SignUpView()
                }
            }
        }
    }
}