package com.evg_ivanoff.rickmortycharacterswiki.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.evg_ivanoff.rickmortycharacterswiki.databinding.ActivityCharInfoBinding
import com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels.CharacterViewModel
import com.squareup.picasso.Picasso

class CharInfoActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }
    private val binding by lazy {
        ActivityCharInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(!intent.hasExtra("char_id")){
            finish()
            return
        }
        val char_id = intent.getIntExtra("char_id", 1)

        setupActivity(char_id)

    }

    private fun setupActivity(char_id: Int) {
        viewModel.initData(char_id)
        lifecycleScope.launchWhenStarted {
            viewModel.charSF.collect {
                if (it.id != -1) {
                    binding.textCharName.text = it.name.toString()
                    binding.textCharRace.text = it.species.toString()
                    binding.textCharGender.text = it.gender.toString()
                    binding.textCharStatus.text = it.status.toString()
                    binding.textCharLocation.text = it.location?.name.toString()
                    binding.textCharEpisodes.text = it.episode?.size.toString()
                    Picasso.get().load(it.image).into(binding.imageViewChar)
                }
            }
        }
    }

    companion object{
        fun newIntent(context: Context, char_id: Int): Intent {
            val intent = Intent(context, CharInfoActivity::class.java)
            intent.putExtra("char_id", char_id)
            return intent
        }
    }
}