package com.example.pokedex.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ViewModel para manejar la información del Pokémon
class PokeInfoViewModel : ViewModel() {
    // Inicializa Retrofit para hacer llamadas a la API
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/") // URL base de la API
        .addConverterFactory(GsonConverterFactory.create()) // Convertidor de Gson
        .build()

    // Crea una instancia del servicio de la API
    private val service: ApiService = retrofit.create(ApiService::class.java)

    // LiveData para almacenar la información del Pokémon
    val pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonDescription = MutableLiveData<Pokemon>()

    // Función para obtener información del Pokémon por ID
    fun getPokemonInfo(id: Int) {
        val call = service.getPokemonInfo(id) // Hace la llamada a la API

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon) // Actualiza el LiveData con la respuesta
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel() // Cancela la llamada en caso de fallo
            }
        })
    }

    // Función para obtener la descripción del Pokémon por ID
    fun getPokemonDescription(id: Int) {
        val callDescription = service.getPokemonSpecies(id) // Hace la llamada a la API

        callDescription.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonDescription.postValue(pokemon) // Actualiza el LiveData con la respuesta
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel() // Cancela la llamada en caso de fallo
            }
        })
    }
}
