package com.example.pokedex.ui.theme


import androidx.compose.ui.graphics.Color

// ===== CORES PRIMÁRIAS =====
val PrimaryColor = Color(0xFFFF6B6B)      // Vermelho Pokédex (Poké Ball)
val PrimaryDark = Color(0xFFE63946)       // Vermelho escuro
val PrimaryLight = Color(0xFFFFB0B8)      // Vermelho claro

// ===== CORES SECUNDÁRIAS =====
val SecondaryColor = Color(0xFF1E90FF)    // Azul moderno (tipo Water)
val SecondaryDark = Color(0xFF0066CC)     // Azul escuro
val SecondaryLight = Color(0xFF87CEEB)    // Azul claro

// ===== CORES NEUTRAS =====
val BackgroundColor = Color(0xFFFAFAFA)   // Branco quase puro
val SurfaceColor = Color(0xFFFFFFFF)      // Branco puro
val SurfaceVariant = Color(0xFFF0F0F0)    // Cinza muito claro

// ===== CORES DE TEXTO =====
val TextPrimary = Color(0xFF1F1F1F)       // Cinza escuro (quase preto)
val TextSecondary = Color(0xFF666666)     // Cinza médio
val TextTertiary = Color(0xFF999999)      // Cinza claro

// ===== CORES DE ESTADO =====
val SuccessColor = Color(0xFF52B788)      // Verde sucesso
val ErrorColor = Color(0xFFD62828)        // Vermelho erro
val WarningColor = Color(0xFFF77F00)      // Laranja aviso
val InfoColor = Color(0xFF457B9D)         // Azul informação

// ===== CORES POR TIPO DE POKÉMON =====
object PokemonTypeColors {
    val Normal = Color(0xFFA8A878)
    val Fire = Color(0xFFF08030)
    val Water = Color(0xFF6890F0)
    val Electric = Color(0xFFF8D030)
    val Grass = Color(0xFF78C850)
    val Ice = Color(0xFF98D8D8)
    val Fighting = Color(0xFFC03028)
    val Poison = Color(0xFFA040A0)
    val Ground = Color(0xFFE0C068)
    val Flying = Color(0xFFA890F0)
    val Psychic = Color(0xFFF85888)
    val Bug = Color(0xFFA8B820)
    val Rock = Color(0xFFB8A038)
    val Ghost = Color(0xFF705898)
    val Dragon = Color(0xFF7038F8)
    val Dark = Color(0xFF705848)
    val Steel = Color(0xFFB8B8D0)
    val Fairy = Color(0xFFEE99AC)
}

// ===== CORES DE GRADIENTE (para cards) =====
val GradientStart = Color(0xFFFFEFF0)     // Branco com tint vermelho
val GradientEnd = Color(0xFFF0FFFB)       // Branco com tint azul

// ===== CORES ESPECIAIS =====
val DividerColor = Color(0xFFEBEBEB)      // Para linhas divisórias
val DisabledColor = Color(0xFFCCCCCC)     // Para elementos desativados
val OverlayColor = Color(0x4D000000)      // Overlay semi-transparente (30%)