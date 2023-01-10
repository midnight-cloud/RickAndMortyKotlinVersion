package com.evg_ivanoff.rickmortycharacterswiki.domain.models

data class CharacterModel(
    val id: Int = 0,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val gender: String? = null,
    val location: LocationModel? = null,
    val image: String? = null,
    val episode: List<String>? = null
)