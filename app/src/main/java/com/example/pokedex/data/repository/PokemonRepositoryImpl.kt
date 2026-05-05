package com.example.pokedex.data.repository

import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.PokemonDetail

interface PokemonRepository {

    suspend fun getPokemons(limit: Int = 20, offset: Int = 0): Result<List<Pokemon>>
    suspend fun getPokemonDetail(id: String): Result<PokemonDetail>
    suspend fun searchPokemon(name: String): Result<PokemonDetail>
}