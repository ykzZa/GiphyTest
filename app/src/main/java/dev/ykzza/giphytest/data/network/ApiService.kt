package dev.ykzza.giphytest.data.network

import dev.ykzza.giphytest.data.network.model.GifDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getGifList(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String,
        @Query("bundle") bundle: String
    ): Response<GifDto>
}