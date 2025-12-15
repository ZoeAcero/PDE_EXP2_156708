package com.example.pde_exp2_156708.data.model

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa una película obtenida de la API REST.
 * Utilizamos SerializedName para mapear los campos JSON a las propiedades del objeto Kotlin.
 */
data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("titulo") val title: String,
    @SerializedName("director") val director: String,
    @SerializedName("genero") val genre: String,
    @SerializedName("portada") val imageUrl: String, // URL de la portada de la película
    @SerializedName("anyo") val year: Int,
    @SerializedName("sinopsis") val synopsis: String
)