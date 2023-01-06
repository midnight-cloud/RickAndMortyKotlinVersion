package com.evg_ivanoff.rickmortycharacterswiki.data.repository

import com.evg_ivanoff.rickmortycharacterswiki.data.api.ApiFactory
import com.evg_ivanoff.rickmortycharacterswiki.data.storage.models.MainResponceCharacters
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.InfoModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.LocationModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.MainCharsModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.repository.CharacterRepo
import retrofit2.Response
import com.evg_ivanoff.rickmortycharacterswiki.data.storage.models.Character as MyChar

class CharacterRepoImpl(private val apiFactory: ApiFactory) : CharacterRepo {
    override suspend fun getCharList(): MainCharsModel {
        val charList = apiFactory.apiService.getAllCharacters()
        return mapToDomain(charList)
    }

    override suspend fun getCharListByPage(page: Int): MainCharsModel {
        val charList = apiFactory.apiService.getCharactersByPage(page)
        return mapToDomain(charList)
    }

    override suspend fun getCharById(id: Int): CharacterModel {
        val char = apiFactory.apiService.getCharacterById(id)
        return mapToStorage(char)
    }

    private fun mapToStorage(item: Response<MyChar>): CharacterModel {
        val it = item.body()!!
        return CharacterModel(
            it.id,
            it.name,
            it.status,
            it.species,
            it.gender,
            LocationModel(it.location?.name, it.location?.url),
            it.image,
            it.episode
        )
    }

    private fun mapToDomain(item: Response<MainResponceCharacters>): MainCharsModel {
        val i = item.body()!!
        if (i.dataInfo != null && i.characterOnes != null) {
            return MainCharsModel(
                dataInfo = InfoModel(
                    i.dataInfo.count,
                    i.dataInfo.pages,
                    i.dataInfo.next,
                    i.dataInfo.prev
                ),
                characterOnes = i.characterOnes.map {
                    CharacterModel(
                        it.id,
                        it.name,
                        it.status,
                        it.species,
                        it.gender,
                        LocationModel(it.location?.name, it.location?.url),
                        it.image,
                        it.episode
                    )
                }
            )
        }
        return MainCharsModel(null, null)
    }

}