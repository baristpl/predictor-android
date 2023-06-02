package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentPowerSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PowerSelectionFragment :
    BaseFragment<FragmentPowerSelectionBinding>(FragmentPowerSelectionBinding::inflate) {


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
        val power = binding.editText.text.toString()
        mainViewModel.selectPower(power)
        findNavController().navigate(R.id.mtvSelectionFragment)
    }

    private fun updateContinueButtonEnabled(hasAnyText: Boolean) {
        binding.continueButton.isEnabled = !hasAnyText
    }


}