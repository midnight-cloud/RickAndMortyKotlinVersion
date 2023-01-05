package com.evg_ivanoff.rickmortycharacterswiki.api

import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.Character
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.MainResponceCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/character")
    suspend fun getAllCharacters(): Response<MainResponceCharacters>

    @GET("api/character")
    suspend fun getCharactersByPage(@Query("page") id: Int): Response<MainResponceCharacters>

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>

}