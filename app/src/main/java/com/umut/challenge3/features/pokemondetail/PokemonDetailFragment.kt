package com.umut.challenge3.features.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.umut.challenge3.databinding.FragmentPokemonDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: FragmentPokemonDetailBinding
    private var pokemonName : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater)


        pokemonName = arguments?.getString("id_param")

        lifecycleScope.launch {
            viewModel.getPokemonDetailData(pokemonName)
        }

        viewModel.pokemonDetailLiveData.observe(viewLifecycleOwner) {
            Glide
                .with(this)
                .load(it?.sprites?.frontDefault)
                .into(binding.pokemonImage)

            binding.pokemonDetailText.text = it?.id.toString()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(pokemonName: String?) =
            PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("id_param", pokemonName)
                }
            }
    }
}