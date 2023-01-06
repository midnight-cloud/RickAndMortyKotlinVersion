package com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val charRepo by lazy { CharacterRepoImpl(ApiFactory) }
    private val getCharacterByIdUsecase by lazy { GetCharacterByIdUsecase(charRepo) }

    private val initValue = CharacterModel(-1)
    private val _charSF = MutableStateFlow(initValue)
    val charSF: StateFlow<CharacterModel> = _charSF.asStateFlow()
    //asStateFlow() для readonly

    fun initData(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _charSF.value = getCharacterByIdUsecase.execute(id)
        }
    }
}