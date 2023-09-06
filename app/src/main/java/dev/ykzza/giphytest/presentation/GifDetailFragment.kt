package dev.ykzza.giphytest.presentation

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dev.ykzza.giphytest.R
import dev.ykzza.giphytest.databinding.FragmentGifDetailBinding


class GifDetailFragment : Fragment() {

    private var _binding: FragmentGifDetailBinding? = null
    private val binding
        get() =
            _binding ?: throw RuntimeException("FragmentSecondBinding == null")

    private val args: GifDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            Glide.with(binding.root)
                .load(args.gifUrl)
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
                .error(R.drawable.ic_error)
                .into(ivGif)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}