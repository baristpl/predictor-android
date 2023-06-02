package com.deu.csc.predictor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ListAdapter
import com.deu.csc.predictor.R
import com.deu.csc.predictor.database.AppDao
import com.deu.csc.predictor.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val navHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    private val navController
        get() = navHostFragment.navController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.brandSelectionFragment, R.id.loadingFragment),
        )

        navController.addOnDestinationChangedListener { nav, destination, _ ->
            val shouldBeVisible = destination.id != R.id.loadingFragment
            binding.appbar.isVisible = shouldBeVisible
        }

        supportActionBar?.setDisplayShowTitleEnabled(true)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }

}