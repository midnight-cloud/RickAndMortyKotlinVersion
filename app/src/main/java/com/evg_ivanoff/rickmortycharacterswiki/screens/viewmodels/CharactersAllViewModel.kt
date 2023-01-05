package com.evg_ivanoff.rickmortycharacterswiki.screens.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.evg_ivanoff.rickmortycharacterswiki.api.ApiFactory
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.MainResponceCharacters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CharactersAllViewModel(application: Application) : AndroidViewModel(application) {

    private val _liveData = MutableStateFlow(MainResponceCharacters(null, null))
    val liveData: StateFlow<MainResponceCharacters> = _liveData.asStateFlow()
    //asStateFlow() для readonly

    init {
        loadData()
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            _liveData.value = ApiFactory.apiService.getAllCharacters().body()!!
        }
    }

    fun nextPage(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _liveData.value = ApiFactory.apiService.getCharactersByPage(page).body()!!
        }
    }
}