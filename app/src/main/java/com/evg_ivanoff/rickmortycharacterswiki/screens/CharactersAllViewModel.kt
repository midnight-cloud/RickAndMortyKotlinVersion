package com.evg_ivanoff.rickmortycharacterswiki.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.evg_ivanoff.rickmortycharacterswiki.api.ApiFactory
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CharactersAllViewModel(application: Application): AndroidViewModel(application) {

    private var compositeDisposable = CompositeDisposable()
    var liveData = MutableLiveData<ArrayList<Character>>()

    var charList: ArrayList<Character>? = null

    init {
        loadData()
    }

    fun getDetailInfo(id: Int){

    }

    private fun getCharacterList(): MutableLiveData<ArrayList<Character>> {
        return liveData
    }

    fun loadData(){
        val disposable = ApiFactory.apiService.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                charList = it.characterOnes as ArrayList<Character>
                liveData.value = charList
            },{
                Log.d("TEST_OF_LOAD", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}