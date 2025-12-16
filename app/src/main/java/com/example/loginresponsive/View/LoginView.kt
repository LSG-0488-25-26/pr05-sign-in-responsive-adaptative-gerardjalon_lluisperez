package com.example.loginresponsive.View

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.loginresponsive.R

@Composable
fun LoginView(){
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
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Email")},
                    leadingIcon = { Icon(painterResource(R.drawable.mail), contentDescription = "Email") }
                )
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Password")},
                    leadingIcon = { Icon(painterResource(R.drawable.pass), contentDescription = "Password") }
                )
                Spacer(modifier = Modifier.height(48.dp))
                Button(onClick = { }) {
                    Text(text = "Login")
                }
            }
        }
    }
}
