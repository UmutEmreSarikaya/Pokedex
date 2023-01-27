package com.umut.challenge3.features.pokemonlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umut.challenge3.PokemonDao
import com.umut.challenge3.PokemonService
import com.umut.challenge3.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val service: PokemonService,
    private val pokemonDao: PokemonDao
) : ViewModel() {

    val pokemonLiveData = MutableLiveData<Response?>()

    suspend fun getPokemonData(offset: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.listPokemon(offset, limit)

           for (item in response?.pokemons!!){
                pokemonDao.insert(item)
            }

            viewModelScope.launch(Dispatchers.Main) {
                pokemonLiveData.value = response
            }
        }
    }
}