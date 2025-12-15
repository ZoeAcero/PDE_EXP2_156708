package com.example.pde_exp2_156708.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.pde_exp2_156708.R;

// Esta Activity recibe los detalles de la película a través del Intent
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_TITLE = "extra_movie_title";
    public static final String EXTRA_MOVIE_DIRECTOR = "extra_movie_director";
    public static final String EXTRA_MOVIE_SYNOPSIS = "extra_movie_synopsis";
    public static final String EXTRA_MOVIE_IMAGE_URL = "extra_movie_image_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 1. Obtener la información del Intent
        String title = getIntent().getStringExtra(EXTRA_MOVIE_TITLE);
        String director = getIntent().getStringExtra(EXTRA_MOVIE_DIRECTOR);
        String synopsis = getIntent().getStringExtra(EXTRA_MOVIE_SYNOPSIS);
        String imageUrl = getIntent().getStringExtra(EXTRA_MOVIE_IMAGE_URL);

        // 2. Inicializar Views
        TextView titleTextView = findViewById(R.id.detail_title);
        TextView directorTextView = findViewById(R.id.detail_director);
        TextView synopsisTextView = findViewById(R.id.detail_synopsis);
        ImageView posterImageView = findViewById(R.id.detail_poster);

        // Establecer el título de la Activity
        setTitle(title);

        // 3. Rellenar la información
        titleTextView.setText(title);
        directorTextView.setText("Director: " + director);
        synopsisTextView.setText(synopsis);

        // 4. Cargar la portada con Glide
        Glide.with(this)
                .load(imageUrl)
                .into(posterImageView);
    }
}