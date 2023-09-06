package dev.ykzza.giphytest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.ykzza.giphytest.databinding.FragmentGifDetailBinding


class GifDetailFragment : Fragment() {

    private var _binding: FragmentGifDetailBinding? = null
    private val binding
        get() =
            _binding ?: throw RuntimeException("FragmentSecondBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}