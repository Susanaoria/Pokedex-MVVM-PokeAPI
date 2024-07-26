package com.example.pokedex.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeListViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java)

    val pokemonList = MutableLiveData<List<PokeResult>>()
    val pokemonTypes = MutableLiveData<List<TypeName>>() // Actualizado para coincidir con TypeListResponse

    fun getPokemonList(limit: Int = 800, offset: Int = 0) {
        val call = service.getPokemonList(limit, offset)
        call.enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(call: Call<PokeApiResponse>, response: Response<PokeApiResponse>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun getPokemonTypes() {
        val call = service.getPokemonTypes()
        call.enqueue(object : Callback<TypeListResponse> {
            override fun onResponse(call: Call<TypeListResponse>, response: Response<TypeListResponse>) {
                response.body()?.results?.let { types ->
                    pokemonTypes.postValue(types)
                }
            }

            override fun onFailure(call: Call<TypeListResponse>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun getPokemonByType(type: String) {
        val call = service.getPokemonByType(type)
        call.enqueue(object : Callback<TypeByTypeResponse> {
            override fun onResponse(call: Call<TypeByTypeResponse>, response: Response<TypeByTypeResponse>) {
                response.body()?.pokemon?.let { list ->
                    pokemonList.postValue(list.map { it.pokemon })
                }
            }

            override fun onFailure(call: Call<TypeByTypeResponse>, t: Throwable) {
                call.cancel()
            }
        })
    }
}
