package com.example.pokedex.presentation.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokedex.presentation.ui.theme.PokedexTheme
import com.example.pokedex.presentation.viewmodel.PokemonListViewModel
import com.example.pokedex.test.FakePokemonRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * PokemonListScreenTest: Testa tela de lista com dados fake
 */
@RunWith(AndroidJUnit4::class)
class PokemonListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Testa se header "Pokédex" aparece
     */
    @Test
    fun pokemonListScreen_shouldDisplayHeader() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonListScreen(
                    viewModel = PokemonListViewModel(FakePokemonRepository()),
                    onPokemonClick = {}
                )
            }
        }

        // Verifica se "Pokédex" está visível
        composeTestRule
            .onNodeWithText("Pokédex")
            .assertIsDisplayed()
    }

    /**
     * Testa se search bar aparece
     */
    @Test
    fun pokemonListScreen_shouldDisplaySearchBar() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonListScreen(
                    viewModel = PokemonListViewModel(FakePokemonRepository()),
                    onPokemonClick = {}
                )
            }
        }

        // Verifica se placeholder da busca aparece
        composeTestRule
            .onNodeWithText("Buscar Pokémon...")
            .assertIsDisplayed()
    }

    /**
     * Testa se lista de pokémons aparece
     */
    @Test
    fun pokemonListScreen_shouldDisplayPokemonList() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonListScreen(
                    viewModel = PokemonListViewModel(FakePokemonRepository()),
                    onPokemonClick = {}
                )
            }
        }

        // Aguarda Bulbasaur aparecer
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Bulbasaur")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText("Bulbasaur")
            .assertIsDisplayed()
    }

    /**
     * Testa se Pikachu aparece na lista
     */
    @Test
    fun pokemonListScreen_shouldDisplayPikachu() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonListScreen(
                    viewModel = PokemonListViewModel(FakePokemonRepository()),
                    onPokemonClick = {}
                )
            }
        }

        // Aguarda Pikachu aparecer
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Pikachu")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText("Pikachu")
            .assertIsDisplayed()
    }

    /**
     * Testa se busca funciona
     * Digita "pikachu" no search bar
     */
    @Test
    fun pokemonListScreen_shouldFilterPokemonsBySearch() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonListScreen(
                    viewModel = PokemonListViewModel(FakePokemonRepository()),
                    onPokemonClick = {}
                )
            }
        }

        // Clica no search bar
        composeTestRule
            .onNodeWithText("Buscar Pokémon...")
            .performTextInput("pikachu")

        // Aguarda resultado da busca
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Pikachu")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        // Verifica se Pikachu aparece
        composeTestRule
            .onNodeWithText("Pikachu")
            .assertIsDisplayed()
    }

    /**
     * Testa tipos de pokémon aparecem
     */
    @Test
    fun pokemonListScreen_shouldDisplayPokemonTypes() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonListScreen(
                    viewModel = PokemonListViewModel(FakePokemonRepository()),
                    onPokemonClick = {}
                )
            }
        }

        // Aguarda tipo "Electric" aparecer (Pikachu)
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Electric")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText("Electric")
            .assertIsDisplayed()
    }

    /**
     * Testa se IDs dos pokémons aparecem
     */
    @Test
    fun pokemonListScreen_shouldDisplayPokemonIds() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonListScreen(
                    viewModel = PokemonListViewModel(FakePokemonRepository()),
                    onPokemonClick = {}
                )
            }
        }

        // Aguarda ID #001 aparecer (Bulbasaur)
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("#001")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText("#001")
            .assertIsDisplayed()
    }
}