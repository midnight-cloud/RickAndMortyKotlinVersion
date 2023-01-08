package com.evg_ivanoff.rickmortycharacterswiki.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.evg_ivanoff.rickmortycharacterswiki.databinding.ActivityMainBinding
import com.evg_ivanoff.rickmortycharacterswiki.presenter.adapters.CharsLoadStateAdapter
import com.evg_ivanoff.rickmortycharacterswiki.presenter.adapters.MainCharacterAdapter
import com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels.CharactersAllViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val viewModel: CharactersAllViewModel by viewModels { CharactersAllViewModel.Factory }

    private val adapter = MainCharacterAdapter()
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firstInit()
        clickListeners()
    }

    private fun clickListeners() {
        adapter.onCharClickListener = {
            val intent = CharInfoActivity.newIntent(this@MainActivity, it.id)
            startActivity(intent)
        }
    }

    private fun firstInit() {
        binding.rvCharacterAdapter.layoutManager = GridLayoutManager(applicationContext, 2)
        initAdapter()
        lifecycleScope.launchWhenStarted {
            viewModel.liveData.collectLatest {
                adapter.submitData(it)
                binding.rvCharacterAdapter
            }
        }
    }

    private fun initAdapter() {
        binding.rvCharacterAdapter.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CharsLoadStateAdapter(),
            footer = CharsLoadStateAdapter()
        )
        adapter.addLoadStateListener { state ->
            val refreshState = state.refresh
            binding.rvCharacterAdapter.isVisible = refreshState != LoadState.Loading
            binding.progressbar.isVisible = refreshState == LoadState.Loading
            if (refreshState is LoadState.Error) {
                Snackbar.make(
                    binding.root,
                    refreshState.error.localizedMessage ?: "",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }


}


