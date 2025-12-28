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

}