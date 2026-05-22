package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.data.repository.PokemonRepositoryImpl
import com.example.pokedex.presentation.ui.screens.PokemonDetailScreen
import com.example.pokedex.presentation.ui.screens.PokemonListScreen
import com.example.pokedex.presentation.ui.screens.SplashScreen
import com.example.pokedex.presentation.ui.theme.PokedexTheme
import com.example.pokedex.presentation.viewmodel.PokemonDetailViewModel
import com.example.pokedex.presentation.viewmodel.PokemonListViewModel

class MainActivity : ComponentActivity() {

    private val repository = PokemonRepositoryImpl()

    private val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(PokemonListViewModel::class.java) ->
                    PokemonListViewModel(repository) as T
                modelClass.isAssignableFrom(PokemonDetailViewModel::class.java) ->
                    PokemonDetailViewModel(repository) as T
                else -> throw IllegalArgumentException("ViewModel desconhecido: $modelClass")
            }
        }
    }

    private val listViewModel: PokemonListViewModel by viewModels { factory }
    private val detailViewModel: PokemonDetailViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash") {
                            SplashScreen(
                                onNavigateToList = {
                                    navController.navigate("list") {
                                        popUpTo("splash") { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable("list") {
                            PokemonListScreen(
                                viewModel = listViewModel,
                                onPokemonClick = { pokemonId ->
                                    navController.navigate("detail/$pokemonId")
                                }
                            )
                        }

                        composable("detail/{pokemonId}") { backStackEntry ->
                            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: ""
                            PokemonDetailScreen(
                                viewModel = detailViewModel,
                                pokemonId = pokemonId,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}