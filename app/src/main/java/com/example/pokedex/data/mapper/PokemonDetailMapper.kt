package com.example.pokedex.data.mapper

import com.example.pokedex.data.dto.PokemonDetailDto
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.utils.formatHeight
import com.example.pokedex.utils.formatWeight
import com.example.pokedex.utils.formatPokemonId
import com.example.pokedex.utils.getImageUrlFromSprites

object PokemonDetailMapper {
    fun toDomain(dto: PokemonDetailDto): PokemonDetail {
        return PokemonDetail(
            id = dto.id,
            name = dto.name.capitalize(),
            pokedexId = formatPokemonId(dto.id),

            imageUrl = getImageUrlFromSprites(
                officialArtwork = dto.sprites.other?.officialArtwork?.frontDefault,
                frontDefault = dto.sprites.frontDefault
            ),
            types = dto.types.map { it.type.name.capitalize() },
            height = formatHeight(dto.height),
            weight = formatWeight(dto.weight),
            hp = dto.stats.find { it.stat.name == "hp" }?.baseStat ?: 0,
            attack = dto.stats.find { it.stat.name == "attack" }?.baseStat ?: 0,
            defense = dto.stats.find { it.stat.name == "defense" }?.baseStat ?: 0,
            spAtk = dto.stats.find { it.stat.name == "sp-atk" }?.baseStat ?: 0,
            spDef = dto.stats.find { it.stat.name == "sp-def" }?.baseStat ?: 0,
            speed = dto.stats.find { it.stat.name == "speed" }?.baseStat ?: 0,
            abilities = dto.abilities.map { it.ability.name.capitalize() }
        )
    }
}