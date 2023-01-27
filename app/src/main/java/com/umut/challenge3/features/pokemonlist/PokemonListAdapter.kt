package com.umut.challenge3.features.pokemonlist


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umut.challenge3.Pokemon
import com.umut.challenge3.Response
import com.umut.challenge3.databinding.ItemPokemonBinding

class PokemonListAdapter(
    private val response: Response?,
    val onItemClickListener: (String?) -> Unit
) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonListHolder>() {

    inner class PokemonListHolder(
        private val itemBinding: ItemPokemonBinding,
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemView.setOnClickListener {
                onItemClickListener(response?.pokemons?.get(adapterPosition)?.name)
            }
        }

        fun bindItems(item: Pokemon?) {
            itemBinding.pokemonName.text = item?.name

            val value= item?.url?.split('/')

            Glide
                .with(itemView)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${value?.get(6)}.png")
                .into(itemBinding.pokemonImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListHolder {
        val itemBinding =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PokemonListHolder, position: Int) {
        holder.bindItems(response?.pokemons?.get(position))
    }

    override fun getItemCount() = response?.pokemons?.size!!

}