package com.evg_ivanoff.rickmortycharacterswiki.domain.usecases

import com.evg_ivanoff.rickmortycharacterswiki.domain.models.MainCharsModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.repository.CharacterRepo

class GetCharactersListUsecase(private val charRepo: CharacterRepo) {
    suspend fun execute(): MainCharsModel {
        return charRepo.getCharList()
    }
}