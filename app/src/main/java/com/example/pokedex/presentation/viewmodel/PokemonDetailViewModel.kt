package com.example.pokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repository.Result
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.presentation.state.UiState
import kotlinx.coroutines.launch

/**
 * PokemonDetailViewModel: Gerencia estado do detalhe de um pokémon
 *
 * Responsabilidades:
 * - Carregar detalhes completo de um pokémon por ID
 * - Gerenciar estado (Loading, Success, Error)
 * - Permitir retry se falhar
 */
class PokemonDetailViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    // Detalhe do pokémon
    private val _pokemon = MutableLiveData<UiState<PokemonDetail>>()
    val pokemon: LiveData<UiState<PokemonDetail>> = _pokemon

    // Guarda ID para retry
    private var lastPokemonId: String? = null

    /**
     * Carrega detalhes de um pokémon
     * @param id ID ou nome do pokémon (ex: "25" ou "pikachu")
     */
    fun loadPokemon(id: String) {
        lastPokemonId = id

        viewModelScope.launch {
            _pokemon.value = UiState.Loading

            val result = repository.getPokemonDetail(id)

            _pokemon.value = when (result) {
                is Result.Success -> {
                    UiState.Success(result.data)
                }
                is Result.Error -> {
                    UiState.Error(result.exception.message ?: "Erro ao carregar detalhes")
                }
            }
        }
    }

    /**
     * Tenta carregar novamente (retry)
     */
    fun retry() {
        lastPokemonId?.let { id ->
            loadPokemon(id)
        }
    }
}