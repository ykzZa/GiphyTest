package dev.ykzza.giphytest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ykzza.giphytest.data.GifRepositoryImpl
import dev.ykzza.giphytest.domain.Gif
import dev.ykzza.giphytest.domain.usecase.GetGifListUseCase
import kotlinx.coroutines.launch

class GifsViewModel: ViewModel() {

    private val repository = GifRepositoryImpl()
    val gifList = MutableLiveData<List<Gif>?>()

    private val getGifListUseCase = GetGifListUseCase(repository)

    private var offset: Int = 0

    fun loadGifs() {
        viewModelScope.launch {
            if(gifList.value == null) {
                gifList.value = getGifListUseCase(offset)
            } else {
                val list = gifList.value?.plus(getGifListUseCase(offset))
                gifList.value = list
            }
            offset += 50
        }
    }
}