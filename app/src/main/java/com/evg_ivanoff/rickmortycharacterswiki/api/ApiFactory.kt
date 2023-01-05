package com.evg_ivanoff.rickmortycharacterswiki.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://rickandmortyapi.com/"
    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val _apiService : ApiService? = null

    val apiService = _apiService ?: getInstance().create(ApiService::class.java)
}