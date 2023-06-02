package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentMtvSelectionBinding
import com.deu.csc.predictor.databinding.FragmentPowerSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MtvSelectionFragment :
    BaseFragment<FragmentMtvSelectionBinding>(FragmentMtvSelectionBinding::inflate) {


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
        val mtv = binding.editText.text.toString()
        mainViewModel.selectMtv(mtv)
        findNavController().navigate(R.id.loadingFragment)
    }

    private fun updateContinueButtonEnabled(hasAnyText: Boolean) {
        binding.continueButton.isEnabled = !hasAnyText
    }


}