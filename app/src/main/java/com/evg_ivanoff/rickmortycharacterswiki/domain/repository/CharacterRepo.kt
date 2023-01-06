package com.evg_ivanoff.rickmortycharacterswiki.domain.repository

import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.MainCharsModel

interface CharacterRepo {
    suspend fun getCharList(): MainCharsModel
    suspend fun getCharListByPage(page: Int) :MainCharsModel
    suspend fun getCharById(id: Int): CharacterModel
}