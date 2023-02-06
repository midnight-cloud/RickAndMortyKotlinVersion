package com.evg_ivanoff.rickmortycharacterswiki.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.evg_ivanoff.rickmortycharacterswiki.R
import com.evg_ivanoff.rickmortycharacterswiki.databinding.ActivityMainBinding
import com.evg_ivanoff.rickmortycharacterswiki.presenter.application.appComponent
import com.evg_ivanoff.rickmortycharacterswiki.presenter.fragments.MainFragment

class MainActivity : AppCompatActivity() {



    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.main_fragment_place, MainFragment.newInstance())
            }
        } else {
            supportFragmentManager.commit {
                replace(R.id.main_fragment_place, MainFragment.newInstance())
                setReorderingAllowed(true)
            }
        }
    }



}


