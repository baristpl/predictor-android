package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentKmSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KmSelectionFragment :
    BaseFragment<FragmentKmSelectionBinding>(FragmentKmSelectionBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.editText.addTextChangedListener {
            updateContinueButtonEnabled(it.isNullOrBlank())
        }
        binding.continueButton.setOnClickListener {
            completeSelection()
        }
    }

    private fun completeSelection() {
        val km = binding.editText.text.toString()
        mainViewModel.selectKm(km)
        findNavController().navigate(R.id.tramerSelectionFragment)
    }

    private fun updateContinueButtonEnabled(hasAnyText: Boolean) {
        binding.continueButton.isEnabled = !hasAnyText
    }


}