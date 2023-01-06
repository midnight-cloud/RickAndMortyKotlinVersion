package com.evg_ivanoff.rickmortycharacterswiki.data.storage

import com.evg_ivanoff.rickmortycharacterswiki.data.storage.models.Character
import com.evg_ivanoff.rickmortycharacterswiki.data.storage.models.MainResponceCharacters
import retrofit2.Response

class CharsStorageImpl: CharacterStorage {
    override fun getCharList(): Response<MainResponceCharacters> {
        TODO("Not yet implemented")
    }

    override fun getCharListByPage(page: Int): Response<MainResponceCharacters> {
        TODO("Not yet implemented")
    }

    override fun getCharById(id: Int): Response<Character> {
        TODO("Not yet implemented")
    }
}