package com.example.pokedex.presentation.state

/**
 * UiState: Sealed class que representa TODOS os possíveis estados da UI
 *
 * ❌ PROBLEMA QUE RESOLVE:
 * Sem isso, ViewModel precisaria de múltiplas variáveis para controlar estados:
 * - isLoading: Boolean
 * - data: List<Pokemon>?
 * - error: String?
 * - isEmpty: Boolean
 *
 * Isso fica confuso! Múltiplas variáveis podem estar "true" ao mesmo tempo.
 * Se isLoading=true E data!=null, qual estado usar?
 *
 * ✅ SOLUÇÃO:
 * UiState é EXCLUSIVAMENTE um de:
 * - Loading (carregando)
 * - Success<T> (sucesso com dados)
 * - Error (erro com mensagem)
 * - Empty (nenhum dado)
 *
 * Apenas UM estado por vez! Impossível estados conflitantes.
 *
 * 🎯 USO NO VIEWMODEL:
 * val pokemons: LiveData<UiState<List<Pokemon>>> = _pokemons.asLiveData()
 *
 * 🎯 USO NA COMPOSABLE:
 * when (val state = viewModel.pokemons.observeAsState().value) {
 *     is UiState.Loading -> LoadingScreen()
 *     is UiState.Success -> SuccessScreen(state.data)
 *     is UiState.Error -> ErrorScreen(state.message)
 *     is UiState.Empty -> EmptyScreen()
 * }
 */

sealed class UiState<out T> {

    /**
     * Loading: Indica que os dados estão sendo carregados
     *
     * ❌ QUANDO USAR:
     * - Chamou repository.getPokemons()
     * - Aguardando resposta da API
     * - Não há dados na tela ainda
     *
     * 🎨 NA UI:
     * Mostra ProgressBar/Spinner (loading indicator)
     *
     * 📍 TRANSIÇÃO:
     * Loading → Success (se API retornar dados)
     * Loading → Error (se API falhar)
     * Loading → Empty (se API retornar lista vazia)
     */
    object Loading : UiState<Nothing>()

    /**
     * Success<T>: Indica sucesso e contém os dados
     *
     * ❌ GENÉRICO <T>:
     * Funciona com qualquer tipo de dado:
     * - Success<List<Pokemon>>
     * - Success<PokemonDetail>
     * - Success<Int> (para paginação)
     *
     * 🎨 NA UI:
     * Mostra os dados (lista, cards, detalhes)
     *
     * 📍 EXEMPLO:
     * is UiState.Success<List<Pokemon>> -> {
     *     LazyGrid {
     *         items(state.data) { pokemon ->
     *             PokemonCard(pokemon)
     *         }
     *     }
     * }
     *
     * 💾 data: T
     * Armazena QUALQUER tipo (Pokemon, Int, String, etc)
     */
    data class Success<T>(val data: T) : UiState<T>()

    /**
     * Error: Indica erro e contém mensagem
     *
     * ❌ QUANDO USAR:
     * - API retornou erro (4xx, 5xx)
     * - Sem conexão de internet
     * - JSON parsing falhou
     * - Timeout na requisição
     *
     * 🎨 NA UI:
     * Mostra:
     * - Ícone de erro ❌
     * - Mensagem descritiva
     * - Botão "Tentar Novamente" (retry)
     *
     * 📍 EXEMPLO:
     * is UiState.Error -> {
     *     ErrorScreen(
     *         message = state.message,
     *         onRetry = { viewModel.loadPokemons() }
     *     )
     * }
     *
     * 💬 message: String
     * Exemplo: "Erro de conexão. Verifique sua internet."
     */
    data class Error(val message: String) : UiState<Nothing>()

    /**
     * Empty: Indica sucesso mas sem dados
     *
     * ❌ QUANDO USAR:
     * - Busca retornou 0 resultados
     * - Filtro resultou em lista vazia
     * - Primeira vez carregando e não há pokémons
     *
     * 🎨 NA UI:
     * Mostra:
     * - Ícone vazio 🔍
     * - Mensagem: "Nenhum pokémon encontrado"
     * - Sugestão: "Tente outra busca"
     *
     * 📍 EXEMPLO:
     * is UiState.Empty -> {
     *     EmptyScreen(
     *         title = "Nenhum pokémon encontrado",
     *         message = "Tente buscar por outro nome"
     *     )
     * }
     *
     * ⚠️ DIFERENÇA DE ERROR:
     * Empty: É esperado (usuário buscou, não encontrou)
     * Error: É não-esperado (falha na API)
     */
    object Empty : UiState<Nothing>()
}

/**
 * ===== EXEMPLO DE USO REAL =====
 *
 * // No ViewModel:
 * class PokemonListViewModel(
 *     private val repository: PokemonRepository
 * ) : ViewModel() {
 *
 *     private val _pokemons = MutableLiveData<UiState<List<Pokemon>>>()
 *     val pokemons: LiveData<UiState<List<Pokemon>>> = _pokemons
 *
 *     fun loadPokemons() {
 *         _pokemons.value = UiState.Loading  // Mostra spinner
 *
 *         viewModelScope.launch {
 *             val result = repository.getPokemons(page = 0, limit = 20)
 *
 *             _pokemons.value = when (result) {
 *                 is Result.Success -> {
 *                     if (result.data.isEmpty()) {
 *                         UiState.Empty  // Nenhum pokémon
 *                     } else {
 *                         UiState.Success(result.data)  // Sucesso com dados
 *                     }
 *                 }
 *                 is Result.Error -> {
 *                     UiState.Error(result.exception.message ?: "Erro desconhecido")
 *                 }
 *             }
 *         }
 *     }
 * }
 *
 * // Na Composable:
 * @Composable
 * fun PokemonListScreen(viewModel: PokemonListViewModel) {
 *     val state = viewModel.pokemons.observeAsState()
 *
 *     when (val currentState = state.value) {
 *         is UiState.Loading -> {
 *             Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
 *                 CircularProgressIndicator()
 *             }
 *         }
 *         is UiState.Success -> {
 *             LazyVerticalGrid(
 *                 columns = GridCells.Fixed(2),
 *                 modifier = Modifier.fillMaxSize()
 *             ) {
 *                 items(currentState.data.size) { index ->
 *                     PokemonCard(currentState.data[index])
 *                 }
 *             }
 *         }
 *         is UiState.Error -> {
 *             ErrorScreen(
 *                 message = currentState.message,
 *                 onRetry = { viewModel.loadPokemons() }
 *             )
 *         }
 *         is UiState.Empty -> {
 *             EmptyScreen(title = "Nenhum pokémon encontrado")
 *         }
 *         null -> {} // Ainda não iniciou
 *     }
 * }
 */