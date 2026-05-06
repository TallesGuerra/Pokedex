package com.example.pokedex.utils

/**
 * Formata altura de decímetros para centímetros
 * Exemplo: 7 dm → "0.7 m"
 */
fun formatHeight(decimeters: Int): String {
    val meters = decimeters / 10.0
    return String.format("%.1f m", meters)
}

/**
 * Formata peso de hectogramas para quilogramas
 * Exemplo: 69 hg → "6.9 kg"
 */
fun formatWeight(hectograms: Int): String {
    val kilograms = hectograms / 10.0
    return String.format("%.1f kg", kilograms)
}

/**
 * Formata ID do pokémon com 3 dígitos
 * Exemplo: 1 → "#001", 25 → "#025"
 */
fun formatPokemonId(id: Int): String {
    return String.format("#%03d", id)
}