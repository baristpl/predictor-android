package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentModelSelectionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ModelSelectionFragment :
    BaseFragment<FragmentModelSelectionBinding>(FragmentModelSelectionBinding::inflate) {

    private lateinit var adapter: TextAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val models = mainViewModel.getModels()
            adapter = TextAdapter(models, ::onModelItemClick)
            binding.recycler.adapter = adapter
        }
    }

    private fun onModelItemClick(series: String) {
        mainViewModel.selectModel(series)
        findNavController().navigate(R.id.yearSelectionFragment)

    }

}