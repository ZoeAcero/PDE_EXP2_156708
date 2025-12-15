package com.example.pde_exp2_156708.ui.settings;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.example.pde_exp2_156708.R;
import com.example.pde_exp2_156708.data.repository.PrefsRepository;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Cargar las preferencias desde el archivo res/xml/preferences.xml
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Configurar el resumen del ListPreference (opcional, pero buena práctica)
        // Opcional: Esto muestra el valor actual de la preferencia
        findPreference(PrefsRepository.PREF_KEY_LAYOUT).setOnPreferenceChangeListener((preference, newValue) -> {
            // Aquí puedes añadir lógica si necesitas hacer algo inmediatamente después del cambio
            // Por ejemplo, mostrar un Toast:
            // Toast.makeText(getContext(), "Vista cambiada a: " + newValue.toString(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}