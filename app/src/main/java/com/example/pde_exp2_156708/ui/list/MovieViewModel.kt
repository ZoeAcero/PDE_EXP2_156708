package com.example.pde_exp2_156708.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pde_exp2_156708.data.model.Movie
import com.example.pde_exp2_156708.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    // Exponer la lista de películas como LiveData
    val movies: LiveData<List<Movie>> = repository.movies

    init {
        // Al crearse el ViewModel, iniciar la carga de datos en un hilo de fondo (coroutine)
        fetchMovies()
    }

    /**
     * Inicia la llamada al repositorio para obtener las películas.
     */
    fun fetchMovies() {
        // viewModelScope es adecuado para tareas de red y bases de datos
        // Se ejecuta en un hilo de fondo
        viewModelScope.launch {
            repository.fetchMovies()
        }
    }
}