package com.evg_ivanoff.rickmortycharacterswiki.domain.models


data class MainCharsModel(
    val dataInfo: InfoModel? = null,
    val characterOnes: List<CharacterModel>? = null
)