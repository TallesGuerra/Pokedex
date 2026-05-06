package com.example.pokedex.data.dto

import com.google.gson.annotations.SerializedName


data class PokemonDto(
    val name: String,
    val url: String
) {
    // Extrai ID da URL: "https://pokeapi.co/api/v2/pokemon/1/" → 1
    val id: Int
        get() = url.split("/").filter { it.isNotEmpty() }.last().toIntOrNull() ?: 0
}


data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDto>
)