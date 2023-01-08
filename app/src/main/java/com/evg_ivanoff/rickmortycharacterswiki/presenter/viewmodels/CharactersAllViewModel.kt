package com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.evg_ivanoff.rickmortycharacterswiki.data.api.ApiPageSource
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class CharactersAllViewModel() : ViewModel() {

    private var _liveData: StateFlow<PagingData<CharacterModel>> = Pager(
        PagingConfig(pageSize = 3)
    ) {
        ApiPageSource()
    }.flow
        .cachedIn(viewModelScope)   // for recreate activity (orientation change)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    val liveData: StateFlow<PagingData<CharacterModel>> = _liveData

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CharactersAllViewModel()
            }
        }
    }

}