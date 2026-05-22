package com.example.pokedex.utils

fun formatHeight(decimeters: Int): String {
    val meters = decimeters / 10.0
    return String.format("%.1f m", meters)
}

fun formatWeight(hectograms: Int): String {
    val kilograms = hectograms / 10.0
    return String.format("%.1f kg", kilograms)
}

fun formatPokemonId(id: Int): String {
    return String.format("#%03d", id)
}