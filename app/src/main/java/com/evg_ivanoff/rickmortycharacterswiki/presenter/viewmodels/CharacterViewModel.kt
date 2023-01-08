package com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evg_ivanoff.rickmortycharacterswiki.data.api.ApiFactory
import com.evg_ivanoff.rickmortycharacterswiki.data.repository.CharacterRepoImpl
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.usecases.GetCharacterByIdUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterByIdUsecase: GetCharacterByIdUsecase
) : ViewModel() {


    private val initValue = CharacterModel(-1)
    private val _charSF = MutableStateFlow(initValue)
    val charSF: StateFlow<CharacterModel> = _charSF.asStateFlow()
    //asStateFlow() для readonly

    fun initData(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _charSF.value = getCharacterByIdUsecase.execute(id)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val charRepo by lazy(LazyThreadSafetyMode.NONE) { CharacterRepoImpl(ApiFactory) }
                val getCharacterByIdUsecase by lazy(LazyThreadSafetyMode.NONE) {
                    GetCharacterByIdUsecase(charRepo)
                }
                CharacterViewModel(getCharacterByIdUsecase)
            }
        }
    }
}