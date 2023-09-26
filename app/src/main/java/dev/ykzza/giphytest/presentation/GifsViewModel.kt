package dev.ykzza.giphytest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ykzza.giphytest.domain.Gif
import dev.ykzza.giphytest.domain.GifRepository
import dev.ykzza.giphytest.domain.usecase.GetGifListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifsViewModel @Inject constructor(
    private val getGifListUseCase: GetGifListUseCase
): ViewModel() {

    val gifList = MutableLiveData<List<Gif>?>()
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