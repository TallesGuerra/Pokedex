package com.example.pokedex.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.presentation.state.UiState
import com.example.pokedex.presentation.ui.theme.BackgroundColor
import com.example.pokedex.presentation.ui.theme.PrimaryColor
import com.example.pokedex.presentation.ui.theme.SurfaceColor
import com.example.pokedex.presentation.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel,
    pokemonId: String,
    onBackClick: () -> Unit
) {
    LaunchedEffect(pokemonId) {
        viewModel.loadPokemon(pokemonId)
    }

    val uiState by viewModel.pokemon.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // Header com botão voltar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryColor)
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }
        }

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
                val pokemon = (uiState as UiState.Success<PokemonDetail>).data
                PokemonDetailContent(pokemon = pokemon)
            }

            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                ErrorScreen(message) {
                    viewModel.retry()
                }
            }

            else -> {}
        }
    }
}

@Composable
fun PokemonDetailContent(pokemon: PokemonDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Imagem grande
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(240.dp)
                .background(SurfaceColor, RoundedCornerShape(12.dp))
                .padding(16.dp),
            contentScale = ContentScale.Fit,

        )

        // Nome + ID
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = pokemon.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = pokemon.pokedexId,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Tipos
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            pokemon.types.forEach { type ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(PrimaryColor.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = type,
                        fontSize = 14.sp,
                        color = PrimaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Informações físicas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceColor, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InfoBox(label = "Altura", value = pokemon.height)
            InfoBox(label = "Peso", value = pokemon.weight)
        }

        // Stats
        StatsSection(pokemon = pokemon)

        // Habilidades
        AbilitiesSection(abilities = pokemon.abilities)
    }
}

@Composable
fun InfoBox(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun StatsSection(pokemon: PokemonDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceColor, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Stats",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        listOf(
            "HP" to pokemon.hp,
            "Ataque" to pokemon.attack,
            "Defesa" to pokemon.defense,
            "Ataque Esp." to pokemon.spAtk,
            "Defesa Esp." to pokemon.spDef,
            "Velocidade" to pokemon.speed
        ).forEach { (label, value) ->
            StatBar(label = label, value = value)
        }
    }
}

@Composable
fun StatBar(label: String, value: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurface
        )

        Box(
            modifier = Modifier
                .weight(2f)
                .height(8.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(minOf(1f, value / 150f))
                    .height(8.dp)
                    .background(PrimaryColor, RoundedCornerShape(4.dp))
            )
        }

        Text(
            text = "$value",
            fontSize = 12.sp,
            modifier = Modifier.weight(0.5f),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AbilitiesSection(abilities: List<String>) {
    if (abilities.isEmpty()) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceColor, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Habilidades",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        abilities.forEach { ability ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        PrimaryColor.copy(alpha = 0.1f),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = ability,
                    fontSize = 14.sp,
                    color = PrimaryColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}