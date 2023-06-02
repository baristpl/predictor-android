package com.deu.csc.predictor.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {
    private const val BASE_URL = "http://54.204.214.128:8000/"

    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val okkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okkHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(
        serviceClass: Class<S>?
    ): S {
        return retrofit.create(serviceClass)
    }
}