package com.evg_ivanoff.rickmortycharacterswiki.data.repository

import com.evg_ivanoff.rickmortycharacterswiki.data.api.ApiService
import com.evg_ivanoff.rickmortycharacterswiki.data.storage.models.MainResponceCharacters
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.InfoModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.LocationModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.MainCharsModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.repository.CharacterRepo
import retrofit2.HttpException
import retrofit2.Response
import com.evg_ivanoff.rickmortycharacterswiki.data.storage.models.Character as MyChar

class CharacterRepoImpl(private val apiFactory: ApiService) : CharacterRepo {
    override suspend fun getCharList(): MainCharsModel {
        val apiResponce = apiFactory.getAllCharacters()
        if (apiResponce.isSuccessful)
            return mapToDomain(apiResponce)
        else
            throw HttpException(apiResponce)
    }

    override suspend fun getCharListByPage(page: Int): MainCharsModel {
        val apiResponce = apiFactory.getCharactersByPage(page)
        if (apiResponce.isSuccessful)
            return mapToDomain(apiResponce)
        else
            throw HttpException(apiResponce)
    }

    override suspend fun getCharById(id: Int): CharacterModel {
        val apiResponce = apiFactory.getCharacterById(id)
        if (apiResponce.isSuccessful)
            return mapToStorage(apiResponce)
        else
            throw HttpException(apiResponce)
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