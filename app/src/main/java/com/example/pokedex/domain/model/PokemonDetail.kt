package com.example.pokedex.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val pokedexId: String,
    val imageUrl: String,
    val types: List<String>,
    val height: String,         // "0.4 m"
    val weight: String,         // "6.0 kg"
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAtk: Int,             // Special Attack
    val spDef: Int,             // Special Defense
    val speed: Int,
    val abilities: List<String>, // ["Static", "Lightning Rod"]
    val description: String = ""  // Descrição do pokédex (opcional)
)