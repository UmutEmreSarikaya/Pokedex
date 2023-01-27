package com.umut.challenge3


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("count") val count: Int? = 0,
    @SerializedName("next") val next: String? = "",
    @SerializedName("previous") val previous: String? = "",
    @SerializedName("results") val pokemons: List<Pokemon?>?
)

@Entity
data class Pokemon(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo @SerializedName("name") val name: String? = "",
    @ColumnInfo @SerializedName("url") val url: String? = ""
)


