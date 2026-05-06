package com.example.pokedex.utils

object Constants {
    // PokéAPI Base URL
    const val POKEMON_BASE_URL = "https://pokeapi.co/api/v2/"

    // Endpoints
    const val ENDPOINT_POKEMON = "pokemon"

    // Paginação
    const val DEFAULT_LIMIT = 20
    const val DEFAULT_OFFSET = 0

    // Timeouts
    const val CONNECT_TIMEOUT = 15L
    const val READ_TIMEOUT = 15L
    const val WRITE_TIMEOUT = 15L
}