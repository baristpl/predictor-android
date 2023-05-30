package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentSeriesSelectionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SeriesSelectionFragment :
    BaseFragment<FragmentSeriesSelectionBinding>(FragmentSeriesSelectionBinding::inflate) {

    private lateinit var adapter: TextAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val series = mainViewModel.getSeries()
            adapter = TextAdapter(series, ::onSeriesItemClick)
            binding.recycler.adapter = adapter
        }
    }

    private fun onSeriesItemClick(series: String) {
        mainViewModel.selectSeries(series)
        findNavController().navigate(R.id.modelSelectionFragment)
    }
}