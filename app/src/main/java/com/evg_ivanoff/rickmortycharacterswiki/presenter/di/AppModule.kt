package com.evg_ivanoff.rickmortycharacterswiki.presenter.di

import android.app.Application
import android.content.Context
import com.evg_ivanoff.rickmortycharacterswiki.domain.usecases.GetCharacterByIdUsecase
import com.evg_ivanoff.rickmortycharacterswiki.presenter.adapters.MainCharacterAdapter
import com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels.CharacterAllViewModelFactory
import com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels.CharacterViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    fun provideCharacterViewModelFactory(getCharacterByIdUsecase: GetCharacterByIdUsecase):
            CharacterViewModelFactory {
        return CharacterViewModelFactory(getCharacterByIdUsecase)
    }

    @Provides
    fun provideCharactersAllViewModelFactory(): CharacterAllViewModelFactory {
        return CharacterAllViewModelFactory()
    }

    @Provides
    fun provideMainCharacterAdapter(): MainCharacterAdapter {
        return MainCharacterAdapter()
    }
}