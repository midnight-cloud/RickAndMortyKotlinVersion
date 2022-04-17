package com.evg_ivanoff.rickmortycharacterswiki.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.evg_ivanoff.rickmortycharacterswiki.R
import com.evg_ivanoff.rickmortycharacterswiki.api.ApiFactory
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.Character
import com.evg_ivanoff.rickmortycharacterswiki.screens.adapters.MainCharacterAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_char_info.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CharactersAllViewModel
    private var nextPage: Int? = 2
    private var prevPage: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainCharacterAdapter()
        rvCharacterAdapter.layoutManager = GridLayoutManager(applicationContext, 2)
        if(prevPage == null){
            btnPrev.hide();
        } else {
            btnPrev.show();
        }
        if(nextPage == null){
            btnNext.hide();
        } else {
            btnNext.show();
        }
        btnNext.setOnClickListener {
            downloadPage(nextPage!!, viewModel)
        }

        btnPrev.setOnClickListener {
            downloadPage(prevPage!!, viewModel)
        }

        adapter.onCharClickListener = object : MainCharacterAdapter.OnCharacterClickListener{
            override fun onCharClick(character: Character) {
                Log.d("TEST_OF_LOAD", character.name.toString())
                val char_id = character.id
                val intent = CharInfoActivity.newIntent(this@MainActivity, char_id)
                Log.d("TEST_OF_LOAD", char_id.toString())
                startActivity(intent)
            }
        }
        rvCharacterAdapter.adapter = adapter

        viewModel = ViewModelProvider(this)[CharactersAllViewModel::class.java]
        viewModel.liveData.observe(this, Observer {
            adapter.characterList = it
        })

    }

    private fun downloadPage(page: Int, viewModel: CharactersAllViewModel){
        val disposable = ApiFactory.apiService.getCharactersByPage(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val charList = it.characterOnes as ArrayList<Character>
                viewModel.liveData.value = charList
                nextPage = asId(it.dataInfo?.next)
                prevPage = asId(it.dataInfo?.prev)
                if(prevPage == null){
                    btnPrev.hide();
                } else {
                    btnPrev.show();
                }
                if(nextPage == null){
                    btnNext.hide();
                } else {
                    btnNext.show();
                }
                Log.d("TEST_OF_LOAD", it.toString())
            },{
                Log.d("TEST_OF_LOAD", it.message.toString())
            })
    }

    fun asId(url: String?): Int? {
        val res = url?.substring(url.lastIndexOf('=') + 1)?.toInt()
        Log.d("TEST_OF_LOAD", res.toString())
        return res
    }
}


