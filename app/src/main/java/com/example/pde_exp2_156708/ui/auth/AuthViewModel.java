package com.example.pde_exp2_156708.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.pde_exp2_156708.data.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

class AuthViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    // Exponer el estado del usuario y los errores a la UI
    val user: LiveData<FirebaseUser> = authRepository.getCurrentUser();
    val error: LiveData<String> = authRepository.getError();

    private val _validationError = MutableLiveData<String>();
    val validationError: LiveData<String> = _validationError

    /**
     * Valida la contraseña según la política restrictiva y luego intenta el registro.
     * Longitud mínima 8, Mayúsculas, Minúsculas, Numéricos.
     */
    fun register(email: String, password: String) {
        if (!isPasswordValid(password)) {
            return // La validación falló, el error ya está en _validationError
        }
        // Si la validación en el cliente es exitosa, intentar el registro en Firebase
        authRepository.register(email, password);
    }

    /**
     * Intenta iniciar sesión.
     */
    fun login;(email: String, password: String) {
        authRepository.login(email, password);
    }

    /**
     * Comprueba que la contraseña cumple con la política restrictiva.
     */
    private fun isPasswordValid;(password: String): Boolean {
        if (password.length < 8) {
            _validationError.value = "La contraseña debe tener al menos 8 caracteres."
            return false;
        }
        if (!password.matches(".*[A-Z].*".toRegex())) {
            _validationError.value = "La contraseña debe incluir al menos una mayúscula."
            return false;
        }
        if (!password.matches(".*[a-z].*".toRegex())) {
            _validationError.value = "La contraseña debe incluir al menos una minúscula."
            return false;
        }
        if (!password.matches(".*[0-9].*".toRegex())) {
            _validationError.value = "La contraseña debe incluir al menos un carácter numérico."
            return false;
        }
        _validationError.value = null // Limpiar errores si la validación es exitosa
        return true;
    }
}