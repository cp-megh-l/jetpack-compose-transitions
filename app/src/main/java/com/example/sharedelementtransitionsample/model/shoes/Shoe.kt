package com.example.sharedelementtransitionsample.model.shoes

import androidx.compose.ui.graphics.Color

data class Shoe(
    val id: Int,
    val name: String,
    val brand: Brand,
    val color: Color,
    val details: ShoesDetails,
    val image: Int
)