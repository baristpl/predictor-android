package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentTransmissionSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransmissionSelectionFragment :
    BaseFragment<FragmentTransmissionSelectionBinding>(FragmentTransmissionSelectionBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.automaticCard.setOnClickListener {
            updateSelection(TransmissionType.AUTOMATIC)
        }
        binding.manuelCard.setOnClickListener {
            updateSelection(TransmissionType.MANUEL)
        }
        binding.continueButton.setOnClickListener {
            completeSelection()
        }
    }

    private fun completeSelection() {
        findNavController().navigate(R.id.powerSelectionFragment)
    }

    private fun updateSelection(type: TransmissionType) {
        val alreadySelected = type == mainViewModel.transmission
        mainViewModel.transmission = if (alreadySelected) TransmissionType.NONE else type

        updateSelectedCard()
        updateContinueButtonEnabled()
    }

    private fun updateContinueButtonEnabled() {
        binding.continueButton.isEnabled =
            mainViewModel.transmission != TransmissionType.NONE
    }

    private fun updateSelectedCard() {
        clearAllCardSelection()

        val card = when (mainViewModel.transmission) {
            TransmissionType.AUTOMATIC -> binding.automaticCard
            TransmissionType.MANUEL -> binding.manuelCard
            TransmissionType.NONE -> null
        }

        card?.isChecked = true
    }

    private fun clearAllCardSelection() {
        binding.manuelCard.isChecked = false
        binding.automaticCard.isChecked = false
    }

    enum class TransmissionType {
        MANUEL, AUTOMATIC, NONE;

        fun toSpecValue(): String {
            return when (this) {
                MANUEL -> "manuel"
                AUTOMATIC -> "automatic"
                else -> ""
            }
        }
    }
}