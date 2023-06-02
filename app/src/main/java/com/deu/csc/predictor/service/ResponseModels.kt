package com.deu.csc.predictor.service

import com.google.gson.annotations.SerializedName


data class PredictionResponse(
    @SerializedName("price")
    val price: Int
)