package com.example.pokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repository.Result
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.presentation.state.UiState
import kotlinx.coroutines.launch


class PokemonListViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    // Lista atual de pokémons
    private val _pokemons = MutableLiveData<UiState<List<Pokemon>>>()
    val pokemons: LiveData<UiState<List<Pokemon>>> = _pokemons

    // Controle de paginação
    private var currentOffset = 0
    private var currentLimit = 20
    private var allPokemonsList = mutableListOf<Pokemon>()

    // Busca
    private var currentSearchQuery = ""

    fun loadPokemons() {
        viewModelScope.launch {
            _pokemons.value = UiState.Loading
            currentOffset = 0
            allPokemonsList.clear()

            val result = repository.getPokemons(limit = currentLimit, offset = currentOffset)

            _pokemons.value = when (result) {
                is Result.Success -> {
                    allPokemonsList.addAll(result.data)
                    if (result.data.isEmpty()) {
                        UiState.Empty
                    } else {
                        UiState.Success(result.data)
                    }
                }
                is Result.Error -> {
                    UiState.Error(result.exception.message ?: "Erro desconhecido")
                }
            }
        }
    }


    fun loadMore() {
        viewModelScope.launch {
            // Se já é sucesso, mostra loading parcial
            val currentData = (_pokemons.value as? UiState.Success<List<Pokemon>>)?.data ?: emptyList()

            currentOffset += currentLimit

            val result = repository.getPokemons(limit = currentLimit, offset = currentOffset)

            _pokemons.value = when (result) {
                is Result.Success -> {
                    allPokemonsList.addAll(result.data)
                    UiState.Success(allPokemonsList.toList())
                }
                is Result.Error -> {
                    UiState.Error(result.exception.message ?: "Erro ao carregar mais")
                }
            }
        }
    }


    fun search(query: String) {
        currentSearchQuery = query
        viewModelScope.launch {
            if (query.isEmpty()) {
                // Se vazio, volta para lista completa
                loadPokemons()
                return@launch
            }

            _pokemons.value = UiState.Loading

            val result = repository.searchPokemon(query)

            _pokemons.value = when (result) {
                is Result.Success -> {
                    UiState.Success(listOf(result.data.let { detail ->
                        Pokemon(
                            id = detail.id,
                            name = detail.name,
                            pokedexId = detail.pokedexId,
                            imageUrl = detail.imageUrl,
                            types = detail.types,
                            hp = detail.hp,
                            attack = detail.attack,
                            defense = detail.defense,
                            spAtk = detail.spAtk,
                            spDef = detail.spDef,
                            speed = detail.speed
                        )
                    }))
                }
                is Result.Error -> {
                    UiState.Empty  // Nenhum pokémon encontrado
                }
            }
        }
    }


    fun retry() {
        if (currentSearchQuery.isEmpty()) {
            loadPokemons()
        } else {
            search(currentSearchQuery)
        }
    }
}