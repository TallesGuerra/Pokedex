package com.example.pokedex.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokedex.presentation.ui.theme.PokedexTheme
import com.example.pokedex.presentation.viewmodel.PokemonDetailViewModel
import com.example.pokedex.test.FakePokemonRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * PokemonDetailScreenTest: Testa tela de detalhe com dados fake
 *
 * ✅ Por que usar FakePokemonRepository?
 * - Sem chamadas reais de rede
 * - Dados consistentes
 * - Testes rápidos e confiáveis
 * - Não precisa de internet
 */
@RunWith(AndroidJUnit4::class)
class PokemonDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Testa se botão voltar aparece
     */
    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun pokemonDetailScreen_shouldDisplayBackButton() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonDetailScreen(
                    viewModel = PokemonDetailViewModel(FakePokemonRepository()),
                    pokemonId = "25",
                    onBackClick = {}
                )
            }
        }

        // Verifica se ícone de voltar está visível
        composeTestRule
            .onNodeWithContentDescription("Voltar")
            .assertIsDisplayed()
    }

    /**
     * Testa se clicar em voltar chama callback
     */
    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun pokemonDetailScreen_shouldCallOnBackClickWhenBackButtonClicked() {
        var backClicked = false

        composeTestRule.setContent {
            PokedexTheme {
                PokemonDetailScreen(
                    viewModel = PokemonDetailViewModel(FakePokemonRepository()),
                    pokemonId = "25",
                    onBackClick = {
                        backClicked = true
                    }
                )
            }
        }

        // Clica no botão voltar
        composeTestRule
            .onNodeWithContentDescription("Voltar")
            .performClick()

        // Verifica se callback foi chamado
        assert(backClicked)
    }

    /**
     * Testa se nome do pokémon aparece
     * Aguarda carregamento com waitUntil
     */
    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun pokemonDetailScreen_shouldDisplayPokemonName() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonDetailScreen(
                    viewModel = PokemonDetailViewModel(FakePokemonRepository()),
                    pokemonId = "25",
                    onBackClick = {}
                )
            }
        }

        // Aguarda "Pikachu" aparecer (com timeout)
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Pikachu")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        // Verifica se Pikachu está visível
        composeTestRule
            .onNodeWithText("Pikachu")
            .assertIsDisplayed()
    }

    /**
     * Testa se ID do pokémon aparece
     */
    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun pokemonDetailScreen_shouldDisplayPokemonId() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonDetailScreen(
                    viewModel = PokemonDetailViewModel(FakePokemonRepository()),
                    pokemonId = "25",
                    onBackClick = {}
                )
            }
        }

        // Aguarda ID
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("#025")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText("#025")
            .assertIsDisplayed()
    }

    /**
     * Testa se seção de stats aparece
     */
    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun pokemonDetailScreen_shouldDisplayStatsSection() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonDetailScreen(
                    viewModel = PokemonDetailViewModel(FakePokemonRepository()),
                    pokemonId = "25",
                    onBackClick = {}
                )
            }
        }

        // Aguarda "Stats" aparecer
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Stats")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText("Stats")
            .assertIsDisplayed()
    }

    /**
     * Testa se seção de habilidades aparece
     */
    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun pokemonDetailScreen_shouldDisplayAbilitiesSection() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonDetailScreen(
                    viewModel = PokemonDetailViewModel(FakePokemonRepository()),
                    pokemonId = "25",
                    onBackClick = {}
                )
            }
        }

        // Aguarda "Habilidades" aparecer
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Habilidades")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText("Habilidades")
            .assertIsDisplayed()
    }

    /**
     * Testa se tipo do pokémon aparece
     */
    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun pokemonDetailScreen_shouldDisplayPokemonType() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonDetailScreen(
                    viewModel = PokemonDetailViewModel(FakePokemonRepository()),
                    pokemonId = "25",
                    onBackClick = {}
                )
            }
        }

        // Aguarda "Electric" aparecer (tipo do Pikachu)
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
     * Testa se altura e peso aparecem
     */
    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun pokemonDetailScreen_shouldDisplayHeightAndWeight() {
        composeTestRule.setContent {
            PokedexTheme {
                PokemonDetailScreen(
                    viewModel = PokemonDetailViewModel(FakePokemonRepository()),
                    pokemonId = "25",
                    onBackClick = {}
                )
            }
        }

        // Aguarda altura
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("0.4 m")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithText("0.4 m")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("6.0 kg")
            .assertIsDisplayed()
    }
}