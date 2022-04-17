package com.evg_ivanoff.rickmortycharacterswiki.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.evg_ivanoff.rickmortycharacterswiki.R
import com.evg_ivanoff.rickmortycharacterswiki.api.ApiFactory
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_char_info.*

class CharInfoActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char_info)
        if(!intent.hasExtra("char_id")){
            finish()
            return
        }
        val char_id = intent.getIntExtra("char_id", 1)

        val disposable = ApiFactory.apiService.getCharacterById(char_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TEST_OF_LOAD", it.toString())
                textCharName.text = it.name.toString()
                textCharRace.text = it.species.toString()
                textCharGender.text = it.gender.toString()
                textCharStatus.text = it.status.toString()
                textCharLocation.text = it.location?.name.toString()
                textCharEpisodes.text = it.episode?.size.toString()
                Picasso.get().load(it.image).into(imageViewChar)
            },{
                Log.d("TEST_OF_LOAD", it.message.toString())
            })
        compositeDisposable.add(disposable)




    }
    companion object{
        fun newIntent(context: Context, char_id: Int): Intent {
            val intent = Intent(context, CharInfoActivity::class.java)
            intent.putExtra("char_id", char_id)
            return intent
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}