package com.umut.challenge3

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun listPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Response?//query map
}