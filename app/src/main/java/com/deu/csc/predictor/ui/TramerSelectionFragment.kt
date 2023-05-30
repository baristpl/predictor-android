package com.deu.csc.predictor.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.R
import com.deu.csc.predictor.databinding.FragmentKmSelectionBinding
import com.deu.csc.predictor.databinding.FragmentTramerSelectionBinding
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TramerSelectionFragment :
    BaseFragment<FragmentTramerSelectionBinding>(FragmentTramerSelectionBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.noTramerCard.setOnClickListener {
            updateSelection(Tramer.NO_RECORD)
        }
        binding.noInfoCard.setOnClickListener {
            updateSelection(Tramer.NO_INFO)
        }
        binding.tramerExistCard.setOnClickListener {
            updateSelection(Tramer.EXIST)
        }
        binding.continueButton.setOnClickListener {
            completeSelection()
        }
        binding.editText.addTextChangedListener {
            val hasTramerRecord = mainViewModel.tramer == Tramer.EXIST
            val shouldButtonBeEnabled = hasTramerRecord && !it.isNullOrBlank()
            binding.continueButton.isEnabled = shouldButtonBeEnabled
        }
    }

    private fun completeSelection() {
        val cost = binding.editText.text?.toString()
        mainViewModel.completeTramerSelection(cost)
        findNavController().navigate(R.id.transmissionSelectionFragment)
    }

    private fun updateSelection(tramer: Tramer) {
        val alreadySelected = tramer == mainViewModel.tramer
        mainViewModel.tramer = if (alreadySelected) Tramer.NONE else tramer

        updateCostFieldVisibility()
        updateSelectedCard()
        updateContinueButtonEnabled()
    }

    private fun updateContinueButtonEnabled() {
        binding.continueButton.isEnabled =
            mainViewModel.tramer != Tramer.NONE && mainViewModel.tramer != Tramer.EXIST
    }

    private fun updateSelectedCard() {
        clearAllCardSelection()

        val card = when (mainViewModel.tramer) {
            Tramer.EXIST -> binding.tramerExistCard
            Tramer.NO_INFO -> binding.noInfoCard
            Tramer.NO_RECORD -> binding.noTramerCard
            Tramer.NONE -> null
        }

        card?.isChecked = true
    }

    private fun clearAllCardSelection() {
        binding.tramerExistCard.isChecked = false
        binding.noTramerCard.isChecked = false
        binding.noInfoCard.isChecked = false
    }

    private fun updateCostFieldVisibility() {
        val isVisible = mainViewModel.tramer == Tramer.EXIST
        binding.textLayout.isVisible = isVisible
        binding.costHeader.isVisible = isVisible
        if (!isVisible) {
            binding.editText.text = null
        }
    }

    enum class Tramer {
        NO_RECORD, NO_INFO, EXIST, NONE;

        fun toSpecValue(): String {
            return when(this) {
                EXIST -> "tramer var"
                NO_INFO -> "bilinmiyor"
                NO_RECORD -> "tramer yok"
                else -> ""
            }
        }
    }
}