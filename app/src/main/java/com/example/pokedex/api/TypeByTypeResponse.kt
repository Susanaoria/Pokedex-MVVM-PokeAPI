package com.example.pokedex.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Respuesta de la API para obtener Pokémon filtrados por tipo
data class TypeByTypeResponse(
    @SerializedName("pokemon") @Expose val pokemon: List<PokemonEntry>
)

data class PokemonEntry(
    @SerializedName("pokemon") @Expose val pokemon: PokeResult
)

data class PokemonSummary(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("url") @Expose val url: String
)
