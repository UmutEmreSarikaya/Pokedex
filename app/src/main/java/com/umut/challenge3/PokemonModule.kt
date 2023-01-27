package com.umut.challenge3

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal object PokemonModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideService(
        retrofit: Retrofit
    ): PokemonService {
        return retrofit
            .create(PokemonService::class.java)
    }

    @Provides
    fun provideDetailService(
        retrofit: Retrofit
    ): PokemonDetailService {
        return retrofit
            .create(PokemonDetailService::class.java)
    }

    @Provides
    fun providesMyDao(@ApplicationContext applicationContext: Context): PokemonDao{
        return Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java, "pokemon-database"
        ).build().pokemonDao()
    }

}
