package com.example.pde_exp2_156708.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pde_exp2_156708.R;
import com.example.pde_exp2_156708.data.repository.AuthRepository;
import com.example.pde_exp2_156708.data.repository.PrefsRepository;
import com.example.pde_exp2_156708.ui.auth.AuthActivity;
import com.example.pde_exp2_156708.ui.settings.SettingsActivity;

public class MovieListActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private PrefsRepository prefsRepository;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        prefsRepository = new PrefsRepository(this);
        recyclerView = findViewById(R.id.recycler_view_movies);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Observar la lista de películas
        movieViewModel.getMovies().observe(this, movies -> {
            if (movieAdapter == null) {
                movieAdapter = new MovieAdapter(this, movies);
                recyclerView.setAdapter(movieAdapter);
            } else {
                movieAdapter.updateList(movies);
            }
        });

        // Aplicar la configuración de vista al inicio
        applyLayoutPreference();
    }

    // ** Lógica del Layout Manager **
    @Override
    protected void onResume() {
        super.onResume();
        // Re-aplicar la preferencia al volver de SettingsActivity
        applyLayoutPreference();
        // Asegurarse de que los datos se han cargado (si es necesario un refresh)
        movieViewModel.fetchMovies();
    }

    private void applyLayoutPreference() {
        String layoutPref = prefsRepository.getLayoutPreference();

        if (layoutPref.equals(PrefsRepository.LAYOUT_GRID)) {
            // GridLayoutManager: solo imágenes (2 columnas como ejemplo)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            if (movieAdapter != null) {
                movieAdapter.setViewType(MovieAdapter.VIEW_TYPE_GRID);
            }
        } else {
            // LinearLayoutManager: imagen y título
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            if (movieAdapter != null) {
                movieAdapter.setViewType(MovieAdapter.VIEW_TYPE_LINEAR);
            }
        }
    }


    // ** Menú Superior (Settings y Logout) **

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú; añade ítems a la barra de acción si están presentes.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_settings) {
            // Opción de Settings
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (itemId == R.id.action_logout) {
            // Opción de Logout
            AuthRepository authRepository = new AuthRepository();
            authRepository.logout();
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

            // Navegar de vuelta a la pantalla de autenticación
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}