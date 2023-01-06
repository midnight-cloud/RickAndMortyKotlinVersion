package com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels

import androidx.lifecycle.ViewModel
import com.evg_ivanoff.rickmortycharacterswiki.data.api.ApiFactory
import com.evg_ivanoff.rickmortycharacterswiki.data.repository.CharacterRepoImpl
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.MainCharsModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.usecases.GetCharactersListByPageUsecase
import com.evg_ivanoff.rickmortycharacterswiki.domain.usecases.GetCharactersListUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CharactersAllViewModel : ViewModel() {

    private val charRepo by lazy { CharacterRepoImpl(ApiFactory) }
    private val getCharactersListUsecase by lazy { GetCharactersListUsecase(charRepo) }
    private val getCharactersListByPageUsecase by lazy { GetCharactersListByPageUsecase(charRepo) }

    private val _liveData = MutableStateFlow(MainCharsModel(null, null))
    val liveData: StateFlow<MainCharsModel> = _liveData.asStateFlow()
    //asStateFlow() для readonly

    init {
        loadData()
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            _liveData.value = getCharactersListUsecase.execute()
        }
    }

    fun nextPage(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _liveData.value = getCharactersListByPageUsecase.execute(page)
        }
    }

}