package dev.ykzza.giphytest.domain.usecase

import dev.ykzza.giphytest.domain.Gif
import dev.ykzza.giphytest.domain.GifRepository

class GetGifListUseCase(
    private val repository: GifRepository
) {

    suspend operator fun invoke(offSetKey: Int): List<Gif> = repository.getGifList(offSetKey)
}