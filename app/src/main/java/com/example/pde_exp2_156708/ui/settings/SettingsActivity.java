package com.example.pde_exp2_156708.ui.settings;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pde_exp2_156708.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // Puede ser un layout simple de FrameLayout

        // Mostrar el fragmento de preferencias
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();

        // Agregar botón de vuelta atrás en la Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}