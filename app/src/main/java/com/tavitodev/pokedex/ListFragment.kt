package com.tavitodev.pokedex

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.ClassCastException

class ListFragment : Fragment() {

    interface PokemonSelectedListener{
        fun onPokemonSelected(pokemon: Pokemon)
    }

    private lateinit var pokemonSelectedListener: PokemonSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokemonSelectedListener = try {
            context as PokemonSelectedListener
        } catch (e: ClassCastException){
            throw ClassCastException("$context must implement PokemonSelectListener")
        }
    }

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
            pokemonSelectedListener.onPokemonSelected(it)
        }

        val pokemonList = mutableListOf(
            Pokemon(1,"Bulbasaur", 45, 49, 49, 45, Pokemon.PokemonType.GRASS,"https://onceuponapicture.files.wordpress.com/2017/03/joshua-dunlop-bulbasaur-watermarked.jpg"),
            Pokemon(2, "Charmander", 39,52,43,65, Pokemon.PokemonType.FIRE,"https://static.wikia.nocookie.net/pokemon-and-digimon/images/6/63/Ash_Charmander.png/revision/latest?cb=20160113223827"),
            Pokemon(3,"Squirtle", 44,48,65,43,Pokemon.PokemonType.WATER,"https://static.wikia.nocookie.net/leonhartimvu/images/8/8c/Tierno_Squirtle.png/revision/latest?cb=20190128082527"),
            Pokemon(4,"Raichu",60,90,55,110,Pokemon.PokemonType.ELECTRIC,"https://w7.pngwing.com/pngs/32/988/png-transparent-pokemon-go-pikachu-pokemon-x-and-y-raichu-pokemon-go-carnivoran-dog-like-mammal-wildlife.png"),
            Pokemon(12,"Butterfree", 40, 51, 60, 49, Pokemon.PokemonType.WIND,"https://i0.wp.com/sourcegaming.info/wp-content/uploads/2019/03/Character-Chronicle-Butterfree.jpg?fit=720%2C405&ssl=1"),
            Pokemon(43,"Oddish",60,90,55,110,Pokemon.PokemonType.GRASS,"https://static.wikia.nocookie.net/pokemon/images/0/08/Goh_Oddish.png/revision/latest?cb=20200608093725"),
            Pokemon(46,"Paras", 60, 51, 40, 45, Pokemon.PokemonType.WATER,"https://images2.minutemediacdn.com/image/upload/c_fill,w_720,ar_16:9,f_auto,q_auto,g_auto/shape/cover/sport/dataimagepngbase64iVBORw0KGgoAAAANSUhEUgAAAoAAAAHg-b629f730cb6a0b38e4aa204198ac007a.jpg"),
            Pokemon(28,"Sandslash", 35,50,62,45,Pokemon.PokemonType.GRASS,"https://img.republicworld.com/republic-prod/stories/promolarge/xhdpi/y8vrhvukq5tv8c9u_1619777255.jpeg"),
            Pokemon(49, "Venomoth", 50,45,48,58, Pokemon.PokemonType.WIND,"https://static.wikia.nocookie.net/pokemon/images/3/39/Goh_Venomoth.png/revision/latest?cb=20191222145309"),
            Pokemon(67,"Machoke", 50,52,65,49,Pokemon.PokemonType.FIGHTER,"https://media.sketchfab.com/models/b67e8903dd214106b141fd6d31ca2002/thumbnails/cb64ab83cd064d15974be6ec243837c0/92222a35e6904849974607b0af202748.jpeg"),
            Pokemon(125,"Electabuzz",80,70,65,100,Pokemon.PokemonType.ELECTRIC,"https://lh3.googleusercontent.com/proxy/3o6KtWSENjZosyYupT-TwGxRkfvMvxz8Pl8zkRfr90ffLcZSSccXAf5ABeF89MkOoxgs16Nvt2DYnVdxyIfauhNjy_jHLDQXRNc_rDJdMOHLAsoerLRkRVv9c_VKYxro9knRP-xxQnfon_U"),
            Pokemon(16, "Pidgey", 60,55,44,70, Pokemon.PokemonType.WIND,"https://static.fandomspot.com/images/11/10211/00-featured-pidgeotto-flying-in-pokemon-anime.jpg"),
            Pokemon(78, "Rapidash", 53,62,48,85, Pokemon.PokemonType.FIRE,"https://cdn2.bulbagarden.net/upload/thumb/4/41/Rapidash_anime.png/250px-Rapidash_anime.png"),
            Pokemon(119,"Seaking", 60,72,45,59,Pokemon.PokemonType.WATER,"https://static.wikia.nocookie.net/pokemon/images/7/71/Ash_Seaking.png/revision/latest?cb=20150908033048"),
            Pokemon(57,"Primeape",65,80,55,95,Pokemon.PokemonType.FIGHTER,"https://gamepress.gg/pokemongo/sites/pokemongo/files/styles/banner_image/public/2021-01/Primeape.png?itok=cJqbIjLq"),

        )
        adapter.submitList(pokemonList)

        return view
    }
}