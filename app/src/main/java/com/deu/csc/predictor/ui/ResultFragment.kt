package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.deu.csc.predictor.databinding.FragmentLoadingBinding
import com.deu.csc.predictor.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResultFragment :
    BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {
    private val args: ResultFragmentArgs by navArgs()

    private lateinit var adapter: TextAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val values = mainViewModel.gatherSpecValues()

        adapter = TextAdapter(values, {}, TextAdapter.ViewType.Table)
        binding.recycler.adapter = adapter

        binding.resultText.text = args.result
    }

}

