package com.umut.challenge3.features.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.umut.challenge3.R
import com.umut.challenge3.databinding.FragmentPokemonListBinding
import com.umut.challenge3.features.pokemondetail.PokemonDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private var pageCounter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPokemonListBinding.inflate(layoutInflater)

        pokemonListAdapter = PokemonListAdapter(::onItemClickListener)
        binding.recyclerPokemon.adapter = pokemonListAdapter

        showOnRecyclerView()

        binding.buttonPrevious.setOnClickListener {
            if (pageCounter != 0) {
                pageCounter -= 20
            }
            showOnRecyclerView()
        }

        binding.buttonNext.setOnClickListener {
            if (pageCounter != 1140) {
                pageCounter += 20
            }
            showOnRecyclerView()
        }

        return binding.root
    }

    private fun showOnRecyclerView() {
        lifecycleScope.launch {
            viewModel.getPokemonData(pageCounter, 20)
        }

        viewModel.pokemonLiveData.observe(viewLifecycleOwner) {
            pokemonListAdapter.setPokemonList(it?.pokemons)
        }
    }

    private fun onItemClickListener(pokemonName: String?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, PokemonDetailFragment.newInstance(pokemonName))
            ?.addToBackStack("fragment_stack")?.commit()
    }

}