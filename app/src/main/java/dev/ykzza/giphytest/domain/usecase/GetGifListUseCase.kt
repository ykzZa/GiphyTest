package dev.ykzza.giphytest.domain.usecase

import dev.ykzza.giphytest.domain.Gif
import dev.ykzza.giphytest.domain.GifRepository
import javax.inject.Inject

class GetGifListUseCase @Inject constructor(
    private val repository: GifRepository
) {

    suspend operator fun invoke(offSetKey: Int): List<Gif> = repository.getGifList(offSetKey)
}