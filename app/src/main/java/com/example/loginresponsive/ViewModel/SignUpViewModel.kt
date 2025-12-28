package com.example.loginresponsive.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginresponsive.Model.SignUpUiState

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableLiveData(SignUpUiState())
    val uiState: LiveData<SignUpUiState> = _uiState

    fun onNomSencerChange(nom: String) {
        _uiState.value = _uiState.value?.copy(nomSencer = nom)
    }
    fun onDataNaixementChange(data: String) {
        _uiState.value = _uiState.value?.copy(dataNaixement = data)
    }
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value?.copy(email = email)
    }
    fun onTelefonChange(telefon: String) {
        _uiState.value = _uiState.value?.copy(telefon = telefon)
    }
    fun onNomUsuariChange(usuari: String) {
        _uiState.value = _uiState.value?.copy(nomUsuari = usuari)
    }
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value?.copy(password = password)
    }
    fun onConfirmPasswordChange(confirm: String) {
        _uiState.value = _uiState.value?.copy(confirmPassword = confirm)
    }
    fun onTermesAcceptatsChange(accepted: Boolean) {
        _uiState.value = _uiState.value?.copy(termesAcceptats = accepted)
    }


    fun esFormularioValido(): Boolean {
        val state = _uiState.value ?: return false
        return state.nomSencer.isNotBlank() &&
                state.nomUsuari.isNotBlank() &&
                state.password.isNotBlank() &&
                state.termesAcceptats &&
                validarEmail(state.email) &&
                state.password.length >= 6 &&
                state.password == state.confirmPassword &&
                validarFecha(state.dataNaixement) == null &&
                validarTelefono(state.telefon) == null
    }

    private fun validarEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return email.isNotBlank() && emailRegex.matches(email.trim())
    }

    fun validarFecha(fecha: String): String? {
        val fechaRegex = Regex("^\\d{2}/\\d{2}/\\d{4}$")
        if (fecha.isBlank()) return "Data obligatòria."
        if (!fechaRegex.matches(fecha)) return "Format (DD/MM/AAAA)."
        val partes = fecha.split("/")
        val dia = partes[0].toIntOrNull() ?: return "Error"
        val mes = partes[1].toIntOrNull() ?: return "Error"
        if (dia !in 1..31 || mes !in 1..12) return "Data invàlida."
        return null
    }

    fun validarTelefono(telefon: String): String? {
        if (telefon.isBlank()) return "Telèfon obligatori."
        if (!telefon.all { it.isDigit() }) return "Només números."
        if (telefon.length < 9) return "Mínim 9 dígits."
        return null
    }
}