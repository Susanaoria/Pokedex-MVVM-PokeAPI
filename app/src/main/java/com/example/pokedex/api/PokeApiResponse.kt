package com.example.pokedex.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// clase de datos para la respuesta de la API de Pokedex
data class PokeApiResponse (
    @Expose @SerializedName("count") val count: Int,         // Número total de Pokémon disponibles
    @Expose @SerializedName("next") val next: String,       // URL para la siguiente página de resultados
    @Expose @SerializedName("previous") val previous: String, // URL para la página anterior de resultados
    @Expose @SerializedName("results") val results: List<PokeResult> // Lista de resultados de Pokémon
)




