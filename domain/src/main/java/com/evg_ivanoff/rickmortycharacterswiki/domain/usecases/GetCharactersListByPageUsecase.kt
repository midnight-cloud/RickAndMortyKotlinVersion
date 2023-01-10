package com.evg_ivanoff.rickmortycharacterswiki.domain.usecases

import com.evg_ivanoff.rickmortycharacterswiki.domain.models.MainCharsModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.repository.CharacterRepo

class GetCharactersListByPageUsecase(private val charRepo: CharacterRepo) {
    suspend fun execute(page: Int): MainCharsModel {
        return charRepo.getCharListByPage(page)
    }
}