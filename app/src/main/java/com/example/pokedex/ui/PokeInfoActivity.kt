package com.example.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokedex.model.PokeInfoViewModel
import com.example.pokedex.databinding.ActivityPokeInfoBinding

// Actividad para mostrar la información detallada de un Pokémon
class PokeInfoActivity : AppCompatActivity() {

    // Instancia del ViewModel para manejar la lógica de la UI
    lateinit var viewModel: PokeInfoViewModel
    // Instancia del binding para acceder a las vistas
    private lateinit var binding: ActivityPokeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Infla el layout usando ViewBinding
        binding = ActivityPokeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa el ViewModel
        viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

        // Configura la UI
        initUI()
    }

    // Configura la UI con la información del Pokémon
    private fun initUI() {
        // Obtiene el ID del Pokémon desde los extras del Intent
        val id = intent.extras?.get("id") as Int

        // Solicita la información del Pokémon
        viewModel.getPokemonInfo(id)

        // Observa los cambios en la información del Pokémon
        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            // Extrae los nombres de los tipos del Pokémon
            val typeNames = pokemon.types.map { it.type.name }
            // Actualiza las vistas con la información del Pokémon
            binding.nameTextView.text = pokemon.name
            binding.heightText.text = "Altura: ${pokemon.height / 10.0}m"
            binding.weightText.text = "Peso: ${pokemon.weight / 10.0} kg"
            binding.typeText.text = "Tipo: ${typeNames.joinToString()}"
            binding.expBaseText.text = "Exp. Base: ${pokemon.baseExperience}"

            // Carga la imagen del Pokémon usando Glide
            Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.imageView)
        })

        // Solicita la descripción del Pokémon
        viewModel.getPokemonDescription(id)

        // Observa los cambios en la descripción del Pokémon
        viewModel.pokemonDescription.observe(this) { pokemon ->
            // Filtra las entradas de texto en español
            val spanishEntries = pokemon.flavorTextEntries.filter { it.language.name == "es" }
            // Obtiene el primer texto en español
            val spanishText = spanishEntries.firstOrNull()?.flavorText
            // Actualiza la vista con la descripción en español
            binding.descriptionText.text = spanishText ?: ""

            // Guarda las entradas en español en el ViewModel
            viewModel.pokemonInfo.value?.spanishFlavorTextEntries =
                spanishEntries.map { it.flavorText }
        }
    }
}
