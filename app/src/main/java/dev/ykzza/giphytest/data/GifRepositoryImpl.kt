package dev.ykzza.giphytest.data

import dev.ykzza.giphytest.data.network.RetrofitBuilder
import dev.ykzza.giphytest.domain.Gif
import dev.ykzza.giphytest.domain.GifRepository

class GifRepositoryImpl: GifRepository {

    private val apiService = RetrofitBuilder.apiService
    override suspend fun getGifList(
        offSetKey: Int
    ): List<Gif> {
        val listGif = mutableListOf<Gif>()
        try {
            val response = apiService.getGifList(
                API_KEY,
                QUERY_KEY,
                LIMIT_KEY,
                offSetKey,
                RATING_KEY,
                LANG_KEY,
                BUNDLE_KEY
            )
            if(response.isSuccessful) {
                response.body()?.data?.map { data ->
                    val gif = Gif(
                        data.id,
                        data.images.original.url,
                        data.images.fixed_height.url
                    )
                    listGif.add(gif)
                }
            }
        } catch (e: Exception) {
            return listGif
        }
        return listGif
    }

    companion object {
        private const val API_KEY = "l7Ly0S7UuMsNQ18EtQWbjSpxcjeieOTJ"
        private const val QUERY_KEY = "animal"
        private const val LIMIT_KEY = 50
        private const val OFFSET_KEY = 0
        private const val RATING_KEY = "g"
        private const val LANG_KEY = "en"
        private const val BUNDLE_KEY = "messaging_non_clips"
    }
}