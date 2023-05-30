package com.deu.csc.predictor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deu.csc.predictor.database.AppDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appDao: AppDao) : ViewModel() {
    private var brand: String? = null
    private var series: String? = null
    private var model: String? = null
    private var km: String? = null
    private var year: String? = null
    private var fuelConsumption: String? = null

    var tramer = TramerSelectionFragment.Tramer.NONE
    var transmission = TransmissionSelectionFragment.TransmissionType.NONE
    var tramerCost: String? = null


    private val _predictionResultSharedFlow = MutableSharedFlow<String>()
    val predictionResultSharedFlow = _predictionResultSharedFlow.asSharedFlow()


    fun selectBrand(brand: String) {
        this.brand = brand
    }

    fun selectSeries(series: String) {
        this.series = series
    }

    fun selectModel(model: String) {
        this.model = model

    }

    suspend fun getModels() = withContext(Dispatchers.IO) {
        appDao.getModelBy(brand!!, series!!)
    }

    suspend fun getSeries() = withContext(Dispatchers.IO) {
        appDao.getSeriesBy(brand!!)
    }

    suspend fun getBrands() = withContext(Dispatchers.IO) {
        appDao.getBrands()
    }

    fun selectKm(km: String) {
        this.km = km
    }

    fun selectYear(year: String) {
        this.year = year
    }

    fun selectFuelConsumption(consumption: String) {
        fuelConsumption = consumption
        predicate()
    }

    fun completeTramerSelection(cost: String?) {
        tramerCost = cost
    }

    private fun predicate() {
        viewModelScope.launch {
            delay(5000)
            _predictionResultSharedFlow.emit("100.000")
        }
    }

    fun gatherSpecValues(): List<String> {
        return listOf(brand!!, series!!, model!!, year!!, km!!, tramer.toSpecValue(), transmission.toSpecValue(), fuelConsumption!!)
    }
}