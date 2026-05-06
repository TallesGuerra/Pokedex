package com.example.pokedex.data.dto

import com.google.gson.annotations.SerializedName



data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,            // em decímetros (7 = 0.7m)
    val weight: Int,            // em hectogramas (69 = 6.9kg)
    val sprites: SpritesDto,
    val types: List<TypeDto>,
    val stats: List<StatDto>,
    val abilities: List<AbilityDto>
)

data class SpritesDto(
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("other")
    val other: OtherSpritesDto?
)

data class OtherSpritesDto(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDto?
)

data class OfficialArtworkDto(
    @SerializedName("front_default")
    val frontDefault: String?
)

data class TypeDto(
    val type: NamedResourceDto
)

data class NamedResourceDto(
    val name: String,
    val url: String
)

data class StatDto(
    val stat: NamedResourceDto,
    @SerializedName("base_stat")
    val baseStat: Int
)

data class AbilityDto(
    val ability: NamedResourceDto,
    @SerializedName("is_hidden")
    val isHidden: Boolean = false
)