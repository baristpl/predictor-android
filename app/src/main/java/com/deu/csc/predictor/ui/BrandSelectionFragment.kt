package com.deu.csc.predictor.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.database.AppDao
import com.deu.csc.predictor.databinding.FragmentBrandSelectionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class BrandSelectionFragment :
    BaseFragment<FragmentBrandSelectionBinding>(FragmentBrandSelectionBinding::inflate) {

    private lateinit var adapter: TextAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val brands = mainViewModel.getBrands()
            adapter = TextAdapter(brands, ::onBrandItemClick)
            binding.recycler.adapter = adapter
        }
    }

    private fun onBrandItemClick(brand: String) {
        mainViewModel.selectBrand(brand)
        findNavController().navigate(R.id.seriesSelectionFragment)
    }
}