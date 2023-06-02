package com.deu.csc.predictor.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ClientService {
    @POST("predicate")
    suspend fun getPricePrediction(
        @Body predictionRequest: PredictionRequest
    ): Response<PredictionResponse>
}



