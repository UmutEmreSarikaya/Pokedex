package com.umut.challenge3

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("height") private val height: Int,
    @SerializedName("weight") private val weight: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val pokemon_name: String,
    @SerializedName("sprites") val sprites: Sprite,
    @SerializedName("types") val types: List<Type>
) {
    data class Sprite(
        @SerializedName("front_default") val frontDefault: String
    )

    data class Ability(
        @SerializedName("ability") val ability: AbilityDetail,
        @SerializedName("is_hidden") val isHidden: Boolean,
        @SerializedName("slot") val slot: Int
    ) {
        data class AbilityDetail(
            @SerializedName("name") val name: String,
            @SerializedName("url") val url: String
        )
    }

    data class Type(
        @SerializedName("type") val type: TypeDetail
    )

    data class TypeDetail(
        @SerializedName("name") val name: String
    )
}
