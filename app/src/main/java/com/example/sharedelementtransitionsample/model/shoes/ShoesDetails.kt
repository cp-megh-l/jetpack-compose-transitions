package com.example.sharedelementtransitionsample.model.shoes

import androidx.compose.ui.graphics.Color

data class ShoesDetails(
    val size: Double,
    val price: Double,
    val description: String,
    val availableColors: List<Color>,
    val ratings: Float
)