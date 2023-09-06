package dev.ykzza.giphytest.presentation

import androidx.recyclerview.widget.DiffUtil
import dev.ykzza.giphytest.domain.Gif

class GifDiffUtil: DiffUtil.ItemCallback<Gif>() {

    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem == newItem
    }

}