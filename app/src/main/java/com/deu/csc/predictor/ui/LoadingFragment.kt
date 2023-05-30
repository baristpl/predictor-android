package com.deu.csc.predictor.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.deu.csc.predictor.databinding.FragmentLoadingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoadingFragment :
    BaseFragment<FragmentLoadingBinding>(FragmentLoadingBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.predictionResultSharedFlow.collect {
                navigateToResultDetail(it)
            }
        }
    }

    private fun initViews() {
        viewLifecycleOwner.lifecycleScope.launch {
            progressInfoFlow.collect {
                binding.info.setTextWithAnimation(textAnimDuration, it)
            }
        }
        setListeners()
    }

    private val progressInfoFlow = flow {
        var i = 0
        while (true) {
            val info = progressInfoPool[i % progressInfoPool.size]
            emit(info)
            delay(textAnimDuration)
            i++
        }
    }

    private fun setListeners() {
    }

    private fun navigateToResultDetail(resultPrice: String) {
        findNavController().navigate(
            LoadingFragmentDirections.actionLoadingFragmentToResultFragment(
                resultPrice
            )
        )

    }

    fun TextView.setTextWithAnimation(durationMillis: Long, text: String) {
        val fadeOut = AlphaAnimation(1F, 0F)
        fadeOut.duration = durationMillis / 2

        val fadeIn = AlphaAnimation(0F, 1F)
        fadeIn.duration = durationMillis / 2

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                this@setTextWithAnimation.animation = fadeIn
                this@setTextWithAnimation.text = text
                this@setTextWithAnimation.animation.start()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })

        this.startAnimation(fadeOut)
    }

    companion object {
        const val textAnimDuration = 1500L
    }
}


private val progressInfoPool = listOf(
    "Analysing The Prompt...",
    "Extracting Features",
    "Warming Up The AI",
    "Applying The Style",
    "Preparing The Goods...",

    )