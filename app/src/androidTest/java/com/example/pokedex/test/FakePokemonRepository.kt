package com.example.pokedex.test

import com.example.pokedex.data.repository.Result
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.domain.repository.PokemonRepository

/**
 * FakePokemonRepository: Implementação fake do Repository para testes
 *
 * ❓ Por que usar Fake ao invés de Mock?
 * - Mock: Simula com Mockito (verificar chamadas, etc)
 * - Fake: Implementação real mas com dados fixos (mais próximo da realidade)
 *
 * ✅ Vantagens do Fake:
 * - Dados consistentes (não aleatórios)
 * - Sem dependência de internet
 * - Rápido (dados em memória)
 * - Comportamento previsível
 */
class FakePokemonRepository : PokemonRepository {

    /**
     * Lista fake de pokémons para teste
     */
    private val fakePokemonsList = listOf(
        createFakePokemon(1, "Bulbasaur"),
        createFakePokemon(2, "Ivysaur"),
        createFakePokemon(3, "Venusaur"),
        createFakePokemon(25, "Pikachu"),
        createFakePokemon(6, "Charizard")
    )

    /**
     * Mapa de pokémons por ID para busca rápida
     */
    private val pokemonDetailMap = mapOf(
        "1" to createFakePokemonDetail(1, "Bulbasaur"),
        "2" to createFakePokemonDetail(2, "Ivysaur"),
        "3" to createFakePokemonDetail(3, "Venusaur"),
        "25" to createFakePokemonDetail(25, "Pikachu"),
        "6" to createFakePokemonDetail(6, "Charizard"),
        "bulbasaur" to createFakePokemonDetail(1, "Bulbasaur"),
        "pikachu" to createFakePokemonDetail(25, "Pikachu"),
        "charizard" to createFakePokemonDetail(6, "Charizard")
    )

    override suspend fun getPokemons(limit: Int, offset: Int): Result<List<Pokemon>> {
        // Simula paginação
        val start = offset
        val end = minOf(offset + limit, fakePokemonsList.size)
        val paginated = if (start < fakePokemonsList.size) {
            fakePokemonsList.subList(start, end)
        } else {
            emptyList()
        }

        return Result.Success(paginated)
    }

    override suspend fun getPokemonDetail(id: String): Result<PokemonDetail> {
        // Busca por ID ou nome
        val detail = pokemonDetailMap[id] ?: pokemonDetailMap[id.lowercase()]

        return if (detail != null) {
            Result.Success(detail)
        } else {
            Result.Error(Exception("Pokémon não encontrado: $id"))
        }
    }

    override suspend fun searchPokemon(name: String): Result<PokemonDetail> {
        // Busca case-insensitive
        val detail = pokemonDetailMap[name.lowercase()]

        return if (detail != null) {
            Result.Success(detail)
        } else {
            Result.Error(Exception("Pokémon não encontrado: $name"))
        }
    }

    // ===== HELPERS PARA CRIAR DADOS FAKE =====

    /**
     * Cria um Pokémon fake para lista
     */
    private fun createFakePokemon(id: Int, name: String): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            pokedexId = String.format("#%03d", id),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/pokemon/other/official-artwork/$id.png",
            types = when (id) {
                1, 2, 3 -> listOf("Grass", "Poison")
                25 -> listOf("Electric")
                6 -> listOf("Fire", "Flying")
                else -> listOf("Normal")
            },
            hp = when (id) {
                1 -> 45
                25 -> 35
                6 -> 78
                else -> 50
            },
            attack = when (id) {
                1 -> 49
                25 -> 55
                6 -> 84
                else -> 50
            },
            defense = when (id) {
                1 -> 49
                25 -> 40
                6 -> 78
                else -> 50
            },
            spAtk = when (id) {
                1 -> 65
                25 -> 50
                6 -> 109
                else -> 50
            },
            spDef = when (id) {
                1 -> 65
                25 -> 50
                6 -> 85
                else -> 50
            },
            speed = when (id) {
                1 -> 45
                25 -> 90
                6 -> 100
                else -> 50
            }
        )
    }

    /**
     * Cria um PokémonDetail fake com todos os dados
     */
    private fun createFakePokemonDetail(id: Int, name: String): PokemonDetail {
        return PokemonDetail(
            id = id,
            name = name,
            pokedexId = String.format("#%03d", id),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/pokemon/other/official-artwork/$id.png",
            types = when (id) {
                1, 2, 3 -> listOf("Grass", "Poison")
                25 -> listOf("Electric")
                6 -> listOf("Fire", "Flying")
                else -> listOf("Normal")
            },
            height = when (id) {
                1 -> "0.7 m"
                25 -> "0.4 m"
                6 -> "1.7 m"
                else -> "1.0 m"
            },
            weight = when (id) {
                1 -> "6.9 kg"
                25 -> "6.0 kg"
                6 -> "90.5 kg"
                else -> "10.0 kg"
            },
            hp = when (id) {
                1 -> 45
                25 -> 35
                6 -> 78
                else -> 50
            },
            attack = when (id) {
                1 -> 49
                25 -> 55
                6 -> 84
                else -> 50
            },
            defense = when (id) {
                1 -> 49
                25 -> 40
                6 -> 78
                else -> 50
            },
            spAtk = when (id) {
                1 -> 65
                25 -> 50
                6 -> 109
                else -> 50
            },
            spDef = when (id) {
                1 -> 65
                25 -> 50
                6 -> 85
                else -> 50
            },
            speed = when (id) {
                1 -> 45
                25 -> 90
                6 -> 100
                else -> 50
            },
            abilities = when (id) {
                1 -> listOf("Overgrow", "Chlorophyll")
                25 -> listOf("Static", "Lightning Rod")
                6 -> listOf("Blaze", "Solar Power")
                else -> listOf("Ability")
            }
        )
    }
}