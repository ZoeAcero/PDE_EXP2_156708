package com.example.pde_exp2_156708.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.pde_exp2_156708.data.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends ViewModel {

    private final AuthRepository authRepository = new AuthRepository();

    // Exponer el estado del usuario y los errores a la UI
    public final LiveData<FirebaseUser> user = authRepository.getCurrentUser();
    public final LiveData<String> error = authRepository.getError();

    private final MutableLiveData<String> _validationError = new MutableLiveData<>();
    public final LiveData<String> validationError = _validationError;

    /**
     * Valida la contraseña según la política restrictiva y luego intenta el registro.
     * Longitud mínima 8, mayúsculas, minúsculas, numéricos.
     */
    public void register(String email, String password) {
        if (!isPasswordValid(password)) {
            return; // La validación falló, el error ya está en _validationError
        }
        // Intentar el registro en Firebase (método de AuthRepository)
        authRepository.register(email, password);
    }

    /**
     * Intenta iniciar sesión.
     */
    public void login(String email, String password) {
        authRepository.login(email, password);
    }

    /**
     * Comprueba que la contraseña cumple con la política restrictiva.
     */
    private boolean isPasswordValid(String password) {
        if (password == null || password.length() < 8) {
            _validationError.setValue("La contraseña debe tener al menos 8 caracteres.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            _validationError.setValue("La contraseña debe incluir al menos una mayúscula.");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            _validationError.setValue("La contraseña debe incluir al menos una minúscula.");
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            _validationError.setValue("La contraseña debe incluir al menos un carácter numérico.");
            return false;
        }
        _validationError.setValue(null); // Limpiar errores si la validación es exitosa
        return true;
    }
}
