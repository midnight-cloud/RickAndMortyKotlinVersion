package com.evg_ivanoff.rickmortycharacterswiki.screens.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.evg_ivanoff.rickmortycharacterswiki.api.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.Character as MyCharacter

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val initValue = MyCharacter(-1)
    private val _charSF = MutableStateFlow(initValue)
    val charSF: StateFlow<MyCharacter> = _charSF.asStateFlow()
    //asStateFlow() для readonly

    fun initData(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _charSF.value = ApiFactory.apiService.getCharacterById(id).body()!!
        }
    }
}