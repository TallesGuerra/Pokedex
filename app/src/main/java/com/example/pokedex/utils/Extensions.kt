package com.example.pokedex.utils

/**
 * Capitalizaa primeira letra da string
 * "pikachu" → "Pikachu"
 */
fun String.capitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}