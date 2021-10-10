package com.tavitodev.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.pokemon_recycler)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = PokemonAdapter()
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            Toast.makeText(requireActivity(), it.name, Toast.LENGTH_SHORT).show()
        }

        val pokemonList = mutableListOf(
            Pokemon(1,"Bulbasaur", 45, 49, 49, 45, Pokemon.PokemonType.GRASS),
            Pokemon(2, "Charmander", 39,52,43,65, Pokemon.PokemonType.FIRE),
            Pokemon(3,"Squirtle", 44,48,65,43,Pokemon.PokemonType.WATER),
            Pokemon(4,"Raichu",60,90,55,110,Pokemon.PokemonType.ELECTRIC),

        )
        adapter.submitList(pokemonList)

        return view
    }
}