package com.example.pokedex.data.api

import com.example.pokedex.data.dto.PokemonDetailDto
import com.example.pokedex.data.dto.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    /**
     * GET /pokemon?limit=20&offset=0
     * Retorna lista paginada de pokémons
     */
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    /**
     * GET /pokemon/{id}
     * Retorna detalhes completos de um pokémon pelo ID ou nome
     * @param id pode ser: 1 (por ID) ou "pikachu" (por nome)
     */
    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: String
    ): PokemonDetailDto

    /**
     * GET /pokemon/{name}
     * Busca pokémon por nome (alias para getPokemonDetail)
     */
    @GET("pokemon/{name}")
    suspend fun searchPokemonByName(
        @Path("name") name: String
    ): PokemonDetailDto
}