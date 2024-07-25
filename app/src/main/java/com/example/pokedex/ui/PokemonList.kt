package com.example.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.model.PokeListAdapter
import com.example.pokedex.model.PokeListViewModel
import com.example.pokedex.databinding.ActivityMainBinding

// Actividad principal que muestra una lista de Pokémon
class PokemonList : AppCompatActivity() {

    // Instancia del ViewModel para manejar la lógica de la lista de Pokémon
    private lateinit var viewModel: PokeListViewModel
    // Instancia del binding para acceder a las vistas
    private lateinit var binding: ActivityMainBinding
    // Adaptador para mostrar la lista de Pokémon en un RecyclerView
    private lateinit var pokeListAdapter: PokeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Infla el layout usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa el ViewModel
        viewModel = ViewModelProvider(this)[PokeListViewModel::class.java]

        // Configura la UI
        initUI()
    }

    // Configura la UI de la actividad
    private fun initUI() {
        // Configura el RecyclerView con un LinearLayoutManager
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador con una acción al hacer clic en un Pokémon
        pokeListAdapter = PokeListAdapter { id ->
            // Crea un Intent para abrir la actividad de información del Pokémon
            val intent = Intent(this, PokeInfoActivity::class.java)
            // Pasa el ID del Pokémon a la actividad de información
            intent.putExtra("id", id)
            // Inicia la actividad
            startActivity(intent)
        }

        // Establece el adaptador en el RecyclerView
        binding.pokelistRecyclerView.adapter = pokeListAdapter

        // Solicita la lista de Pokémon al ViewModel
        viewModel.getPokemonList()

        // Observa los cambios en la lista de Pokémon
        viewModel.pokemonList.observe(this, Observer { list ->
            // Actualiza el adaptador con la nueva lista de Pokémon
            pokeListAdapter.setData(list)
        })
    }
}

