package com.example.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.model.PokeListAdapter
import com.example.pokedex.model.PokeListViewModel
import com.example.pokedex.databinding.ActivityMainBinding

class PokemonList : AppCompatActivity() {

    private lateinit var viewModel: PokeListViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokeListAdapter: PokeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokeListViewModel::class.java]

        initUI()
    }

    private fun initUI() {
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)
        pokeListAdapter = PokeListAdapter { id ->
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        binding.pokelistRecyclerView.adapter = pokeListAdapter

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this, Observer { list ->
            pokeListAdapter.setData(list)
        })

        viewModel.getPokemonTypes()
        viewModel.pokemonTypes.observe(this, Observer { types ->
            val typeNames = types.map { it.name.capitalize() }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, typeNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.typeSpinner.adapter = adapter
        })

        binding.applyFilterButton.setOnClickListener {
            val selectedType = binding.typeSpinner.selectedItem as String
            viewModel.getPokemonByType(selectedType.toLowerCase())
        }
    }
}
