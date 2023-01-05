package com.evg_ivanoff.rickmortycharacterswiki.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.evg_ivanoff.rickmortycharacterswiki.databinding.ActivityMainBinding
import com.evg_ivanoff.rickmortycharacterswiki.screens.adapters.MainCharacterAdapter
import com.evg_ivanoff.rickmortycharacterswiki.screens.viewmodels.CharactersAllViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CharactersAllViewModel::class.java]
    }
    private var nextPage: Int? = 2
    private var prevPage: Int? = null
    private val adapter = MainCharacterAdapter()
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firstInit()
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnNext.setOnClickListener {
            downloadPage(nextPage!!, viewModel)
        }
        binding.btnPrev.setOnClickListener {
            downloadPage(prevPage!!, viewModel)
        }
        adapter.onCharClickListener = {
            val intent = CharInfoActivity.newIntent(this@MainActivity, it.id)
            startActivity(intent)
        }
    }

    private fun firstInit() {
        binding.rvCharacterAdapter.layoutManager = GridLayoutManager(applicationContext, 2)
        if (prevPage == null) {
            binding.btnPrev.hide()
        } else {
            binding.btnPrev.show()
        }
        if (nextPage == null) {
            binding.btnNext.hide()
        } else {
            binding.btnNext.show()
        }
        binding.rvCharacterAdapter.adapter = adapter
        lifecycleScope.launchWhenStarted {
            viewModel.liveData.collect {
                if (it.characterOnes != null) {
                    adapter.submitList(it.characterOnes)
                }
            }
        }
    }

    private fun downloadPage(page: Int, viewModel: CharactersAllViewModel) {
        viewModel.nextPage(page)
        lifecycleScope.launchWhenStarted {
            viewModel.liveData.collect {
                nextPage = asId(it.dataInfo?.next)
                prevPage = asId(it.dataInfo?.prev)
                if (prevPage == null) {
                    binding.btnPrev.hide()
                } else {
                    binding.btnPrev.show()
                }
                if (nextPage == null) {
                    binding.btnNext.hide()
                } else {
                    binding.btnNext.show()
                }
            }
        }
    }

    private fun asId(url: String?): Int? =
        url?.substring(url.lastIndexOf('=') + 1)?.toInt()
}


