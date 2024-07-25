package com.example.pokedex.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.PokeApiResponse
import com.example.pokedex.api.PokeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ViewModel para manejar la lista de Pokémon
class PokeListViewModel : ViewModel() {
    // Inicializa Retrofit para hacer llamadas a la API
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/") // URL base de la API
        .addConverterFactory(GsonConverterFactory.create()) // Convertidor de Gson
        .build()

    // Crea una instancia del servicio de la API
    private val service: ApiService = retrofit.create(ApiService::class.java)

    // LiveData para almacenar la lista de Pokémon
    val pokemonList = MutableLiveData<List<PokeResult>>()

    // Función para obtener la lista de Pokémon
    fun getPokemonList() {
        val call = service.getPokemonList(251, 0) // Hace la llamada a la API con un límite de 251 Pokémon

        call.enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(call: Call<PokeApiResponse>, response: Response<PokeApiResponse>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list) // Actualiza el LiveData con la lista de Pokémon
                }
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                call.cancel() // Cancela la llamada en caso de fallo
            }
        })
    }
}
