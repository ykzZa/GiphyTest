package dev.ykzza.giphytest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.ykzza.giphytest.databinding.FragmentGifsBinding

class GifsFragment : Fragment() {

    private var _binding: FragmentGifsBinding? = null
    private val binding
        get() =
            _binding ?: throw RuntimeException("FragmentFirstBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}