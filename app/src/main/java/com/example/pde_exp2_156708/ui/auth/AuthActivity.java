package com.example.pde_exp2_156708.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.pde_exp2_156708.R;
import com.example.pde_exp2_156708.ui.list.MovieListActivity;
import com.google.firebase.auth.FirebaseUser;

// Implementar ViewBinding (ej. ActivityAuthBinding binding;)
public class AuthActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Inicializar Views
        emailEditText = findViewById(R.id.edit_text_email);
        passwordEditText = findViewById(R.id.edit_text_password);
        Button loginButton = findViewById(R.id.button_login);
        Button registerButton = findViewById(R.id.button_register);

        // Inicializar ViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // Observar el estado del usuario (éxito en login/registro)
        authViewModel.user.observe(this, this::handleAuthSuccess);

        // Observar errores de Firebase
        authViewModel.error.observe(this, this::showFirebaseError);

        // Observar errores de validación de contraseña (en el cliente)
        authViewModel.validationError.observe(this, this::showValidationError);

        // Listeners
        loginButton.setOnClickListener(v ->
                authViewModel.login(emailEditText.getText().toString(), passwordEditText.getText().toString())
        );

        registerButton.setOnClickListener(v ->
                authViewModel.register(emailEditText.getText().toString(), passwordEditText.getText().toString())
        );
    }

    // Manejar el resultado de la autenticación
    private void handleAuthSuccess(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "Autenticación exitosa.", Toast.LENGTH_SHORT).show();
            // Navegar a la Activity principal
            Intent intent = new Intent(AuthActivity.this, MovieListActivity.class);
            startActivity(intent);
            finish(); // Cierra AuthActivity para que el usuario no pueda volver con 'Back'
        }
    }

    private void showFirebaseError(String errorMessage) {
        if (errorMessage != null) {
            Toast.makeText(this, "Error de Firebase: " + errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private void showValidationError(String errorMessage) {
        if (errorMessage != null) {
            Toast.makeText(this, "Error de Contraseña: " + errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Verificar si el usuario ya está logueado al iniciar la Activity
        if (authViewModel.user.getValue() != null) {
            handleAuthSuccess(authViewModel.user.getValue());
        }
    }
}
