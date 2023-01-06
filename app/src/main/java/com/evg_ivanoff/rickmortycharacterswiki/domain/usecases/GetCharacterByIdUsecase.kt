package com.evg_ivanoff.rickmortycharacterswiki.domain.usecases

import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.repository.CharacterRepo

class GetCharacterByIdUsecase(private val charRepo: CharacterRepo) {
    suspend fun execute(id: Int): CharacterModel {
        return charRepo.getCharById(id)
    }
}