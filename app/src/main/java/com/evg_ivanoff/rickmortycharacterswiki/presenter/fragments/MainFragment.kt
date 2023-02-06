package com.evg_ivanoff.rickmortycharacterswiki.presenter.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.evg_ivanoff.rickmortycharacterswiki.R
import com.evg_ivanoff.rickmortycharacterswiki.databinding.FragmentMainBinding
import com.evg_ivanoff.rickmortycharacterswiki.presenter.adapters.CharsLoadStateAdapter
import com.evg_ivanoff.rickmortycharacterswiki.presenter.adapters.MainCharacterAdapter
import com.evg_ivanoff.rickmortycharacterswiki.presenter.application.appComponent
import com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels.CharacterAllViewModelFactory
import com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels.CharactersAllViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var characterAllViewModelFactory: CharacterAllViewModelFactory

    private val viewModel: CharactersAllViewModel by viewModels { characterAllViewModelFactory }

    @Inject
    lateinit var adapter: MainCharacterAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstInit()
        clickListeners()
    }

    private fun clickListeners() {
        adapter.onCharClickListener = {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.main_fragment_place, DetailFragment.newInstance(it.id))
                addToBackStack("main")
            }
        }
    }

    private fun firstInit() {
        binding.rvCharacterAdapter.layoutManager = GridLayoutManager(requireContext(), 2)
        initAdapter()
        lifecycleScope.launch {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment()
    }

}