package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentGuaranteeSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuaranteeSelectionFragment :
    BaseFragment<FragmentGuaranteeSelectionBinding>(FragmentGuaranteeSelectionBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.hasGuaranteeCard.setOnClickListener {
            updateSelection(true)
        }
        binding.noGuaranteeCard.setOnClickListener {
            updateSelection(false)
        }
        binding.continueButton.setOnClickListener {
            completeSelection()
        }
    }

    private fun completeSelection() {
        findNavController().navigate(R.id.transmissionSelectionFragment)
    }

    private fun updateSelection(hasGuarantee: Boolean) {
        val alreadySelected = hasGuarantee == mainViewModel.hasGuarantee
        mainViewModel.hasGuarantee = if (alreadySelected) null else hasGuarantee

        updateSelectedCard()
        updateContinueButtonEnabled()
    }

    private fun updateContinueButtonEnabled() {
        binding.continueButton.isEnabled =
            mainViewModel.hasGuarantee != null
    }

    private fun updateSelectedCard() {
        clearAllCardSelection()

        mainViewModel.hasGuarantee?.let {
            if (it) {
                binding.hasGuaranteeCard
            } else {
                binding.noGuaranteeCard
            }.isChecked = true
        }

    }

    private fun clearAllCardSelection() {
        binding.noGuaranteeCard.isChecked = false
        binding.hasGuaranteeCard.isChecked = false
    }
}