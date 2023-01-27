package com.umut.challenge3.features.pokemondetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umut.challenge3.PokemonDetail
import com.umut.challenge3.PokemonDetailService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val service: PokemonDetailService
) : ViewModel() {

    val pokemonDetailLiveData = MutableLiveData<PokemonDetail?>()

    suspend fun getPokemonDetailData(pokemonName: String?){
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonDetail = service.getPokemonDetail(pokemonName)

            viewModelScope.launch(Dispatchers.Main) {
                pokemonDetailLiveData.value = pokemonDetail
            }
        }
    }
}