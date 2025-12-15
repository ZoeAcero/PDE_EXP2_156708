package com.example.pde_exp2_156708.ui.settings;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pde_exp2_156708.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usa el layout que acabamos de crear (activity_settings)
        setContentView(R.layout.activity_settings);

        // Si no existe un estado guardado, carga el fragmento
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    // Carga el fragmento en el ID R.id.settings_container
                    .replace(R.id.settings_container, new SettingsFragment())
                    .commit();
        }

        // Habilitar el botón de retroceso en la barra de acción
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.settings));
        }
    }

    // Manejar el botón de retroceso de la Action Bar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}