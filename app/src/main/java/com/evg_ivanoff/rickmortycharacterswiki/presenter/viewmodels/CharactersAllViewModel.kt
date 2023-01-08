package com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
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


class CharactersAllViewModel(
    private val getCharactersListUsecase: GetCharactersListUsecase,
    private val getCharactersListByPageUsecase: GetCharactersListByPageUsecase
) : ViewModel() {


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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val charRepo by lazy(LazyThreadSafetyMode.NONE) { CharacterRepoImpl(ApiFactory) }
                val getCharactersListUsecase by lazy(LazyThreadSafetyMode.NONE) {
                    GetCharactersListUsecase(
                        charRepo
                    )
                }
                val getCharactersListByPageUsecase by lazy(LazyThreadSafetyMode.NONE) {
                    GetCharactersListByPageUsecase(
                        charRepo
                    )
                }
                CharactersAllViewModel(getCharactersListUsecase, getCharactersListByPageUsecase)
            }
        }
    }

}