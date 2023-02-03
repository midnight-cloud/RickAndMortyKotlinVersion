package com.evg_ivanoff.rickmortycharacterswiki.presenter.di

import com.evg_ivanoff.rickmortycharacterswiki.data.api.ApiService
import com.evg_ivanoff.rickmortycharacterswiki.data.repository.CharacterRepoImpl
import com.evg_ivanoff.rickmortycharacterswiki.domain.usecases.GetCharacterByIdUsecase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideApiFactory(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideRetrofiServices(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideCharRepo(apiFactory: ApiService): CharacterRepoImpl {
        return CharacterRepoImpl(apiFactory)
    }

    @Provides
    fun provideGetCharacterByIdUsecase(charRepo: CharacterRepoImpl): GetCharacterByIdUsecase {
        return GetCharacterByIdUsecase(charRepo)
    }

}
