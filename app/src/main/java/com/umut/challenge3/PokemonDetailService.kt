package com.umut.challenge3

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailService {
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(@Path("pokemonName") pokemonName: String?): PokemonDetail?
}