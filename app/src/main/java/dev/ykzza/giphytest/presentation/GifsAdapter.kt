package dev.ykzza.giphytest.presentation

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dev.ykzza.giphytest.R
import dev.ykzza.giphytest.databinding.GifItemViewBinding
import dev.ykzza.giphytest.domain.Gif

class GifsAdapter : ListAdapter<Gif, GifsAdapter.GifViewHolder>(GifDiffUtil()) {
    class GifViewHolder(private val binding: GifItemViewBinding) : ViewHolder(binding.root) {

        fun bind(gif: Gif) {
            binding.apply {
                binding.progressBar.visibility = View.VISIBLE
                Glide.with(binding.root)
                    .load(gif.fixedHeightUrl)
                    .error(R.drawable.ic_error)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.visibility = View.INVISIBLE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.visibility = View.INVISIBLE
                            return false
                        }
                    })
                    .into(ivGif)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = GifItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = getItem(position)
        holder.bind(gif)
    }
}