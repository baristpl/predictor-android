package com.deu.csc.predictor.service

import com.google.gson.annotations.SerializedName


data class PredictionRequest(
    @SerializedName("series")
    val series: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("year")
    val year: String,

    @SerializedName("kilometer")
    val kilometer: String,

    @SerializedName("mtv")
    val mtv: String,

    @SerializedName("power")
    val power: String,

    @SerializedName("transmission")
    val transmission: String,

    @SerializedName("guarantee")
    val guarantee: String
)
