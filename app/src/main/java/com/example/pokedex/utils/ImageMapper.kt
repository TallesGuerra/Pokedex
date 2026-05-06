package com.example.pokedex.utils

/**
 * Retorna URL da imagem oficial do pokémon
 * GitHub PokéAPI sprites: https://raw.githubusercontent.com/PokeAPI/sprites/master/pokemon/other/official-artwork/{id}.png
 */
fun getPokemonImageUrl(id: Int): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/pokemon/other/official-artwork/${id}.png"
}

/**
 * Placeholder enquanto carrega imagem
 */
fun getPlaceholderUrl(): String {
    return "https://via.placeholder.com/300?text=Loading"
}