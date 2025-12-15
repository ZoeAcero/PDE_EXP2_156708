package com.example.pde_exp2_156708.data.api;

import com.example.pde_exp2_156708.data.model.Movie;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;


interface MovieApiService {

    @GET("pelis/peliculas")
    Call<List<Movie>> getMovies();

    String BASE_URL = "https://uax.tionazo.com/";
}

