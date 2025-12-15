package com.example.pde_exp2_156708.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

/**
 * Repositorio para gestionar el acceso a SharedPreferences para las preferencias de visualización.
 */
public class PrefsRepository {

    public static final String PREF_KEY_LAYOUT = "layout_preference";
    // Valor por defecto: LinearLayout (mostrar imagen y título)
    public static final String LAYOUT_LINEAR = "linear";
    // Valor para GridLayout (mostrar solo imágenes)
    public static final String LAYOUT_GRID = "grid";

    private final SharedPreferences preferences;

    public PrefsRepository(Context context) {
        // Usa PreferenceManager para acceder a las SharedPreferences por defecto del sistema de preferencias
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Obtiene la preferencia de visualización actual del RecyclerView.
     * @return String que indica el layout ("linear" o "grid").
     */
    public String getLayoutPreference() {
        // Lee la preferencia con la clave definida en preferences.xml
        return preferences.getString(PREF_KEY_LAYOUT, LAYOUT_LINEAR);
    }

    /**
     * Guarda la preferencia de visualización.
     * Aunque el PreferenceFragment ya lo hace automáticamente, este método centraliza la lógica.
     */
    public void saveLayoutPreference(String layout) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_KEY_LAYOUT, layout);
        editor.apply();
    }

    // NOTA: Para este examen, el PreferenceFragment ya maneja el guardado,
    // pero tener el Repositorio centraliza la clave de acceso (PREF_KEY_LAYOUT).
}