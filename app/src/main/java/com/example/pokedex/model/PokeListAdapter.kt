package com.example.pokedex.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.api.PokeResult
import com.example.pokedex.databinding.PokeListBinding

// Adapter para la RecyclerView que muestra una lista de Pokémon
class PokeListAdapter(private val pokemonClick: (Int) -> Unit) : RecyclerView.Adapter<PokeListAdapter.SearchViewHolder>() {
    // Lista de resultados de Pokémon
    private var pokemonList: List<PokeResult> = emptyList()

    // Función para actualizar la lista de Pokémon y notificar cambios
    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged() // Notifica a la RecyclerView que los datos han cambiado
    }

    // Crea nuevos ViewHolder cuando no hay suficientes ViewHolder existentes
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = PokeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    // Devuelve el tamaño de la lista de Pokémon
    override fun getItemCount(): Int {
        return pokemonList.size
    }

    // Vincula los datos del Pokémon con el ViewHolder
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val binding = holder.binding
        val pokemon = pokemonList[position]
        val pokemonId = pokemon.url.split("/").filter { it.isNotEmpty() }.last().toInt() // Extraer ID de la URL
        binding.pokemonText.text = "#${pokemonId} - ${pokemon.name}"

        holder.itemView.setOnClickListener { pokemonClick(pokemonId) }
    }

    // ViewHolder para la lista de Pokémon
    class SearchViewHolder(val binding: PokeListBinding) : RecyclerView.ViewHolder(binding.root)
}
