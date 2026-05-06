package com.example.pokedex.data.mapper

import com.example.pokedex.data.dto.PokemonDto
import com.example.pokedex.data.dto.PokemonDetailDto
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.utils.formatPokemonId
import com.example.pokedex.utils.getPokemonImageUrl

object PokemonMapper {

    fun toDomain(dto: PokemonDetailDto): Pokemon {
        return Pokemon(
            id = dto.id,
            name = dto.name.capitalize(),
            pokedexId = formatPokemonId(dto.id),
            imageUrl = getPokemonImageUrl(dto.id),
            types = dto.types.map { it.type.name.capitalize() },
            hp = dto.stats.find { it.stat.name == "hp" }?.baseStat ?: 0,
            attack = dto.stats.find { it.stat.name == "attack" }?.baseStat ?: 0,
            defense = dto.stats.find { it.stat.name == "defense" }?.baseStat ?: 0,
            spAtk = dto.stats.find { it.stat.name == "sp-atk" }?.baseStat ?: 0,
            spDef = dto.stats.find { it.stat.name == "sp-def" }?.baseStat ?: 0,
            speed = dto.stats.find { it.stat.name == "speed" }?.baseStat ?: 0
        )
    }
}