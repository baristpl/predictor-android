package com.deu.csc.predictor.database

import androidx.room.*


//language=sql
@Dao
interface AppDao {
    @Query("SELECT DISTINCT seri FROM car_brand WHERE marka = :brand")
    suspend fun getSeriesBy(brand: String): List<String>

    @Query("SELECT DISTINCT model FROM car_brand WHERE marka = :brand AND seri = :series")
    suspend fun getModelBy(brand: String, series: String): List<String>

    @Query("SELECT DISTINCT marka FROM car_brand")
    suspend fun getBrands(): List<String>
}