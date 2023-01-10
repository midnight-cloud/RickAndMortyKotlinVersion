package com.evg_ivanoff.rickmortycharacterswiki.data.storage

import com.evg_ivanoff.rickmortycharacterswiki.data.storage.models.Character
import com.evg_ivanoff.rickmortycharacterswiki.data.storage.models.MainResponceCharacters
import retrofit2.Response

interface CharacterStorage {
    fun getCharList(): Response<MainResponceCharacters>
    fun getCharListByPage(page: Int): Response<MainResponceCharacters>
    fun getCharById(id: Int): Response<Character>
}