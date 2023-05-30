package com.deu.csc.predictor.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_brand")
data class ModelEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "marka")
    val brand: String,

    @ColumnInfo(name = "model")
    val model: String?,

    @ColumnInfo(name = "seri")
    val series: String
)