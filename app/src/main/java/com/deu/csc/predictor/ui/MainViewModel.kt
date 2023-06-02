package com.deu.csc.predictor.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deu.csc.predictor.database.AppDao
import com.deu.csc.predictor.service.ClientService
import com.deu.csc.predictor.service.PredictionRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: ClientService,
    private val appDao: AppDao
    ) : ViewModel() {
    private var brand: String? = null
    private var series: String? = null
    private var model: String? = null
    private var km: String? = null
    private var year: String? = null
    private var power: String? = null
    var hasGuarantee: Boolean? = null
    private var mtv: String? = null

    var transmission = TransmissionSelectionFragment.TransmissionType.NONE


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

    fun selectPower(power: String) {
       this.power = power

    }

    fun selectMtv(mtv: String) {
        this.mtv = mtv
        predicate()
    }

    private fun predicate() {
        val guarantee = if (hasGuarantee == true) "1" else "0"
        val request = PredictionRequest(
            series = series!!,
            model = model!!,
            year = year!!,
            kilometer = km!!,
            power = power!!,
            transmission = transmission.toSpecValue(),
            mtv = mtv!!,
            guarantee = guarantee
        )

        viewModelScope.launch {
            val response = service.getPricePrediction(request)
            val result = response.body()?.price.toString()
            _predictionResultSharedFlow.emit(result)
        }
    }

    fun gatherSpecValues(): List<String> {
        val guarantee = if (hasGuarantee == true) "Garantisi var" else "Garantisi yok"
        return listOf(brand!!, series!!, model!!, year!!, km!!, power!!, transmission.toSpecValue(), mtv!!, guarantee)
    }

}