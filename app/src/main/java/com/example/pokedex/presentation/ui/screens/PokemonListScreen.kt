package com.example.pokedex.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.presentation.state.UiState
import com.example.pokedex.presentation.ui.theme.BackgroundColor
import com.example.pokedex.presentation.ui.theme.PrimaryColor
import com.example.pokedex.presentation.ui.theme.SurfaceColor
import com.example.pokedex.presentation.viewmodel.PokemonListViewModel

/**
 * PokemonListScreen: Lista em grid de pokémons
 */
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    onPokemonClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadPokemons()
    }

    val uiState by viewModel.pokemons.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // Header
        PokemonListHeader()

        // Search Bar
        SearchBar(
            query = searchQuery,
            onQueryChange = { newQuery ->
                searchQuery = newQuery
                viewModel.search(newQuery)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Content
        when (uiState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PrimaryColor)
                }
            }

            is UiState.Success -> {
                val pokemonList = (uiState as UiState.Success<List<Pokemon>>).data
                PokemonGrid(
                    pokemons = pokemonList,
                    onPokemonClick = onPokemonClick,
                    onLoadMore = { viewModel.loadMore() }
                )
            }

            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                ErrorScreen(message) {
                    viewModel.retry()
                }
            }

            is UiState.Empty -> {
                EmptyScreen("Nenhum pokémon encontrado") {
                    searchQuery = ""
                    viewModel.loadPokemons()
                }
            }

            else -> {}
        }
    }
}

@Composable
fun PokemonListHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Pokédex",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Buscar Pokémon...") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Buscar")
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Close, contentDescription = "Limpar")
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = SurfaceColor,
            focusedContainerColor = SurfaceColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = PrimaryColor
        ),
        modifier = modifier
    )
}

@Composable
fun PokemonGrid(
    pokemons: List<Pokemon>,
    onPokemonClick: (String) -> Unit,
    onLoadMore: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(pokemons) { pokemon ->
            PokemonCard(
                pokemon = pokemon,
                onClick = { onPokemonClick(pokemon.id.toString()) }
            )
        }

        // Trigger load more no final
        item {
            LaunchedEffect(Unit) {
                onLoadMore()
            }
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Imagem
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier.fillMaxWidth(0.8f),
            contentScale = ContentScale.Fit
        )

        // ID
        Text(
            text = pokemon.pokedexId,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Nome
        Text(
            text = pokemon.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Tipos
        PokemonTypeChips(types = pokemon.types)
    }
}

@Composable
fun PokemonTypeChips(types: List<String>) {
    androidx.compose.foundation.layout.Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        types.forEach { type ->
            Box(
                modifier = Modifier
                    .background(PrimaryColor.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = type,
                    fontSize = 10.sp,
                    color = PrimaryColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("❌ Erro", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(message, fontSize = 14.sp)
            androidx.compose.material3.Button(onClick = onRetry) {
                Text("Tentar Novamente")
            }
        }
    }
}

@Composable
fun EmptyScreen(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("🔍 Sem Resultados", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(message, fontSize = 14.sp)
            androidx.compose.material3.Button(onClick = onRetry) {
                Text("Voltar")
            }
        }
    }
}