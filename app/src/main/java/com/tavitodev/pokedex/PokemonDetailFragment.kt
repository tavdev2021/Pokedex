package com.tavitodev.pokedex

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PokemonDetailFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var hpText: TextView
    private lateinit var attackText: TextView
    private lateinit var defenseText: TextView
    private lateinit var speedText: TextView
    private lateinit var loadingWheel: ProgressBar
    private lateinit var playfab: FloatingActionButton

    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val rootView =  inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        val pokemon = args.pokemon

        imageView = rootView.findViewById(R.id.fragment_detail_image)
        hpText = rootView.findViewById(R.id.fragment_detail_hp)
        attackText = rootView.findViewById(R.id.fragment_detail_attack)
        defenseText = rootView.findViewById(R.id.fragment_detail_defense)
        speedText = rootView.findViewById(R.id.fragment_detail_speed)
        loadingWheel = rootView.findViewById(R.id.loading_wheel)
        playfab = rootView.findViewById(R.id.play_fab)

        setupToolbar(rootView, pokemon.name)
        setPokemonData(pokemon)

        playfab.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(requireActivity(),pokemon.soundId)
            mediaPlayer.start()
        }

        return rootView
    }

    private fun setupToolbar(rootView: View, name: String) {
        val toolbar = rootView.findViewById<androidx.appcompat.widget.Toolbar>(R.id.detail_toolbar)
        toolbar.title = name
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setPokemonData(pokemon: Pokemon){
        loadingWheel.visibility = View.VISIBLE
        Glide.with(this).load(pokemon.imageUrl).listener(object: RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                loadingWheel.visibility = View.GONE
                imageView.setImageResource(R.drawable.image_not_found)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                loadingWheel.visibility = View.GONE
                return false
            }
        }).error(R.drawable.image_not_found).into(imageView)

        hpText.text = getString(R.string.hp_format, pokemon.hp)
        attackText.text = getString(R.string.attack_format, pokemon.attack)
        defenseText.text = getString(R.string.defense_format, pokemon.defense)
        speedText.text = getString(R.string.speed_format, pokemon.speed)

    }
}