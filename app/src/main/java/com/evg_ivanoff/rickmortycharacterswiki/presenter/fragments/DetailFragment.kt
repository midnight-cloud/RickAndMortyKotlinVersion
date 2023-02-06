package com.evg_ivanoff.rickmortycharacterswiki.presenter.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.evg_ivanoff.rickmortycharacterswiki.databinding.FragmentDetailBinding
import com.evg_ivanoff.rickmortycharacterswiki.presenter.application.appComponent
import com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels.CharacterViewModel
import com.evg_ivanoff.rickmortycharacterswiki.presenter.viewmodels.CharacterViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var itemId: Int? = null

    @Inject
    lateinit var characterViewModelFactory: CharacterViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, characterViewModelFactory)[CharacterViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment(itemId)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                }
            }
        )
    }

    private fun setupFragment(char_id: Int?) {
        if (char_id != null) {
            viewModel.initData(char_id)
            lifecycleScope.launch {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(itemId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, itemId)
                }
            }
    }
}