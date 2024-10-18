package com.example.sharedelementtransitionsample.model.movies

data class Movie(
    val url: String,
    val title: String,
    val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut purus eget sapien"
)