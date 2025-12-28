package com.example.loginresponsive.ViewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginresponsive.Model.SignUpUiState
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

class SignUpViewModel: ViewModel() {
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

}
