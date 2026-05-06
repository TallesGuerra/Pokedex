package com.example.pokedex.data.repository

import com.example.pokedex.data.api.RetrofitClient
import com.example.pokedex.data.mapper.PokemonDetailMapper
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.domain.repository.PokemonRepository

class PokemonRepositoryImpl : PokemonRepository {

    override suspend fun getPokemons(limit: Int, offset: Int): Result<List<Pokemon>> {
        return try {
            val response = RetrofitClient.api.getPokemons(limit, offset)

            val pokemonList = response.results.mapNotNull { dto ->
                try {
                    val detail = RetrofitClient.api.getPokemonDetail(dto.id.toString())
                    val pokemonDetail = PokemonDetailMapper.toDomain(detail)
                    Pokemon(
                        id = pokemonDetail.id,
                        name = pokemonDetail.name,
                        pokedexId = pokemonDetail.pokedexId,
                        imageUrl = pokemonDetail.imageUrl,
                        types = pokemonDetail.types,
                        hp = pokemonDetail.hp,
                        attack = pokemonDetail.attack,
                        defense = pokemonDetail.defense,
                        spAtk = pokemonDetail.spAtk,
                        spDef = pokemonDetail.spDef,
                        speed = pokemonDetail.speed
                    )
                } catch (e: Exception) {
                    null
                }
            }

            Result.Success(pokemonList)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPokemonDetail(id: String): Result<PokemonDetail> {
        return try {
            val dto = RetrofitClient.api.getPokemonDetail(id)
            val detail = PokemonDetailMapper.toDomain(dto)
            Result.Success(detail)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun searchPokemon(name: String): Result<PokemonDetail> {
        return try {
            val dto = RetrofitClient.api.searchPokemonByName(name.lowercase())
            val detail = PokemonDetailMapper.toDomain(dto)
            Result.Success(detail)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}