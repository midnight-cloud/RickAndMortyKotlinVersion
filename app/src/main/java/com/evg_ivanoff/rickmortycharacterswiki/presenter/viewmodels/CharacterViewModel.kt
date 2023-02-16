package com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.usecases.GetCharacterByIdUsecase
import com.evg_ivanoff.rickmortycharacterswiki.presenter.di.ActivityScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@ActivityScope
class CharacterViewModel(
    private val getCharacterByIdUsecase: GetCharacterByIdUsecase
) : ViewModel() {


    private val initValue = CharacterModel(-1)
    private val _charSF = MutableStateFlow(initValue)
    val charSF: StateFlow<CharacterModel> = _charSF.asStateFlow()
    //asStateFlow() для readonly

    fun initData(id: Int) {
        viewModelScope.launch {
            _charSF.value = getCharacterByIdUsecase.execute(id)
        }
    }
}

class CharacterViewModelFactory(val getCharacterByIdUsecase: GetCharacterByIdUsecase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return CharacterViewModel(getCharacterByIdUsecase) as T
    }
}