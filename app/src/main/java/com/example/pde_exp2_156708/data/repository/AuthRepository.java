package com.example.pde_exp2_156708.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Repositorio para manejar toda la lógica de autenticación con Firebase.
 */
public class AuthRepository {

    private final FirebaseAuth firebaseAuth;
    private final MutableLiveData<FirebaseUser> liveUser = new MutableLiveData<>();
    private final MutableLiveData<String> liveError = new MutableLiveData<>();

    public AuthRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        // Inicializar el LiveData con el usuario actual si existe
        liveUser.postValue(firebaseAuth.getCurrentUser());
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return liveUser;
    }

    public LiveData<String> getError() {
        return liveError;
    }

    /**
     * Intenta registrar un nuevo usuario con email y contraseña.
     * @param email Correo del usuario.
     * @param password Contraseña (debe ser pre-validada en el ViewModel).
     */
    public void register(String email, String password) {
        liveError.postValue(null); // Limpiar errores anteriores
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        liveUser.postValue(firebaseAuth.getCurrentUser());
                    } else {
                        // Manejo de errores específicos de Firebase (ej. email ya en uso)
                        liveError.postValue(task.getException().getMessage());
                        liveUser.postValue(null);
                    }
                });
    }

    /**
     * Intenta iniciar sesión.
     */
    public void login(String email, String password) {
        liveError.postValue(null);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        liveUser.postValue(firebaseAuth.getCurrentUser());
                    } else {
                        // Manejo de errores (ej. credenciales inválidas)
                        liveError.postValue(task.getException().getMessage());
                        liveUser.postValue(null);
                    }
                });
    }

    /**
     * Cierra la sesión del usuario actual.
     */
    public void logout() {
        firebaseAuth.signOut();
        liveUser.postValue(null); // Notificar a la UI que el usuario ha cerrado sesión
    }
}