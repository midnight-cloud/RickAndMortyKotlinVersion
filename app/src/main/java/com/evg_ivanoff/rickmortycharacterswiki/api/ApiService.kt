package com.evg_ivanoff.rickmortycharacterswiki.api

import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.Character
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.MainResponceCharacters
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/character")
    fun getAllCharacters(): Single<MainResponceCharacters>

    @GET("api/character")
    fun getCharactersByPage(@Query("page") id: Int): Single<MainResponceCharacters>

    @GET("api/character/{id}")
    fun getCharacterById(@Path("id") id: Int): Single<Character>

}