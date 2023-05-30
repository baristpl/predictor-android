package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentYearSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YearSelectionFragment :
    BaseFragment<FragmentYearSelectionBinding>(FragmentYearSelectionBinding::inflate) {

    private lateinit var adapter: TextAdapter

    private val years = IntRange(1980, 2023).reversed().map { it.toString() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TextAdapter(years, ::onBrandItemClick)
        binding.recycler.adapter = adapter
    }

    private fun onBrandItemClick(year: String) {
        mainViewModel.selectYear(year)
        findNavController().navigate(R.id.kmSelectionFragment)
    }

}