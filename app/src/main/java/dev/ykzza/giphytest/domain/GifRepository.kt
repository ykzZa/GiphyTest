package dev.ykzza.giphytest.domain

interface GifRepository {

    suspend fun getGifList(
        offSetKey: Int
    ): List<Gif>
}