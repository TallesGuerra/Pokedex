package com.example.pokedex.presentation.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material3.Icon
import com.example.pokedex.presentation.ui.theme.PrimaryColor

import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToList: () -> Unit) {
    var alpha by remember { mutableFloatStateOf(0f) }

    // Anima a aparição
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(Unit) {
        alpha = 1f  // Começa animação
        delay(2000)  // Aguarda 2 segundos
        onNavigateToList()  // Navega para lista
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Poké Ball (círculo vermelho + branco)
        PokeballIcon(
            modifier = Modifier
                .size(120.dp)
                .alpha(animatedAlpha)
        )

        // Texto Pokédex
        Text(
            text = "Pokédex",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColor,
            modifier = Modifier.alpha(animatedAlpha)
        )

        // Subtítulo
        Text(
            text = "Explore todos os Pokémons",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.alpha(animatedAlpha)
        )
    }
}

@Composable
fun PokeballIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Rounded.Circle,
        contentDescription = "Poké Ball",
        tint = PrimaryColor,
        modifier = modifier
    )
}