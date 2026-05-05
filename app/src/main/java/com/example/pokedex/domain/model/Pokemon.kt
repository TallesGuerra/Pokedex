package com.example.pokedex.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val pokedexId: String,          // "#001", "#025", etc
    val imageUrl: String,
    val types: List<String>,        // ["Grass", "Poison"], ["Electric"], etc
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val spAtk: Int = 0,
    val spDef: Int = 0,
    val speed: Int = 0
)