package com.example.pde_exp2_156708.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pde_exp2_156708.data.api.MovieApiService
import com.example.pde_exp2_156708.data.model.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repositorio para manejar la obtención de la lista de películas desde la API.
 */
class MovieRepository {

    private val apiService: MovieApiService
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    init {
        // Inicialización de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(MovieApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(MovieApiService::class.java)
    }

    /**
     * Obtiene la lista de películas de la API. Debe llamarse desde una coroutine (hilo de fondo).
     */
    suspend fun fetchMovies() {
        try {
            // La llamada a la API se ejecuta en un hilo de fondo (gracias a 'suspend' y coroutines)
            val response = apiService.getMovies()

            if (response.isSuccessful) {
                _movies.postValue(response.body())
            } else {
                Log.e("MovieRepository", "Error al cargar películas: ${response.code()}")
                _movies.postValue(emptyList())
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Excepción al conectar con la API: ${e.message}")
            _movies.postValue(emptyList())
        }
    }
}