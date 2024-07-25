package com.example.pokedex.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//  interfaz para el servicio API de Pokedex usando Retrofit
interface ApiService {
    // Define una llamada GET para obtener la información de un Pokémon por su ID
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>

    // Define una llamada GET para obtener una lista de Pokémon con parámetros de límite y desplazamiento
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokeApiResponse>

    // Define una llamada GET para obtener la información de una especie de Pokémon por su ID
    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int): Call<Pokemon>
}
