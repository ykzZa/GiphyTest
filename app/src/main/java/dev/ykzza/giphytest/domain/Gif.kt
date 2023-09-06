package dev.ykzza.giphytest.domain

data class Gif(
    val id: String,
    val title: String,
    val username: String,
    val originalUrl: String,
    val fixedHeightUrl: String
)