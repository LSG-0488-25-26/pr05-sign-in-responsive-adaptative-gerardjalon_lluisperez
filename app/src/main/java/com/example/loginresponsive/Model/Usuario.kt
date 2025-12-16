package com.example.loginresponsive.Model

data class Usuario(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val contrasena: String,
    val telefono: Int,
    val fechaNacimiento: String
)
