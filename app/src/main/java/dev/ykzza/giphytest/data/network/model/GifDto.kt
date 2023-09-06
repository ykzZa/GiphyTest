package dev.ykzza.giphytest.data.network.model

data class GifDto(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)