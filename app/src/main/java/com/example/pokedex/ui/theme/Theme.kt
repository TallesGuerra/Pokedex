package com.example.pokedex.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ===== COLOR SCHEME LIGHT (PADRÃO) =====
private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,           // Vermelho Pokédex
    onPrimary = Color.White,
    primaryContainer = PrimaryLight,
    onPrimaryContainer = PrimaryDark,

    secondary = SecondaryColor,       // Azul
    onSecondary = Color.White,
    secondaryContainer = SecondaryLight,
    onSecondaryContainer = SecondaryDark,

    tertiary = Color(0xFF7E57C2),     // Roxo (alternativo)
    onTertiary = Color.White,

    background = BackgroundColor,     // Branco com nuance
    onBackground = TextPrimary,

    surface = SurfaceColor,           // Branco puro
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = TextSecondary,

    outline = DividerColor,
    outlineVariant = Color(0xFFC0C0C0),

    error = ErrorColor,
    onError = Color.White,
    errorContainer = Color(0xFFFFCDD2),
    onErrorContainer = ErrorColor
)

// ===== COLOR SCHEME DARK (DARK MODE) =====
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryLight,           // Vermelho claro em dark mode
    onPrimary = PrimaryDark,
    primaryContainer = PrimaryDark,
    onPrimaryContainer = PrimaryLight,

    secondary = SecondaryLight,       // Azul claro em dark mode
    onSecondary = SecondaryDark,
    secondaryContainer = SecondaryDark,
    onSecondaryContainer = SecondaryLight,

    tertiary = Color(0xFFB39DDB),
    onTertiary = Color(0xFF4527A0),

    background = Color(0xFF121212),   // Preto profundo
    onBackground = Color.White,

    surface = Color(0xFF1E1E1E),      // Cinza escuro
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFE0E0E0),

    outline = Color(0xFF5A5A5A),
    outlineVariant = Color(0xFF3F3F3F),

    error = ErrorColor,
    onError = Color.White,
    errorContainer = Color(0xFF5F2C2C),
    onErrorContainer = Color(0xFFFFB4AB)
)

// ===== TEMA PRINCIPAL =====
@Composable
fun PokédexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PokédexTypography,
        content = content
    )
}

// ===== ATALHOS PARA CORES CUSTOMIZADAS =====
@Composable
fun PokemonTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "normal" -> PokemonTypeColors.Normal
        "fire" -> PokemonTypeColors.Fire
        "water" -> PokemonTypeColors.Water
        "electric" -> PokemonTypeColors.Electric
        "grass" -> PokemonTypeColors.Grass
        "ice" -> PokemonTypeColors.Ice
        "fighting" -> PokemonTypeColors.Fighting
        "poison" -> PokemonTypeColors.Poison
        "ground" -> PokemonTypeColors.Ground
        "flying" -> PokemonTypeColors.Flying
        "psychic" -> PokemonTypeColors.Psychic
        "bug" -> PokemonTypeColors.Bug
        "rock" -> PokemonTypeColors.Rock
        "ghost" -> PokemonTypeColors.Ghost
        "dragon" -> PokemonTypeColors.Dragon
        "dark" -> PokemonTypeColors.Dark
        "steel" -> PokemonTypeColors.Steel
        "fairy" -> PokemonTypeColors.Fairy
        else -> PokemonTypeColors.Normal
    }
}