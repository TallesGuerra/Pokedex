package com.example.pokedex.utils

fun getPokemonImageUrl(id: Int): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
}


fun getImageUrlFromSprites(officialArtwork: String?, frontDefault: String?): String {
    return officialArtwork ?: frontDefault ?: ""
}
