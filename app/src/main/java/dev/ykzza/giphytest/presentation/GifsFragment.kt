package dev.ykzza.giphytest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dev.ykzza.giphytest.databinding.FragmentGifsBinding

class GifsFragment : Fragment() {

    private var _binding: FragmentGifsBinding? = null
    private val binding
        get() =
            _binding ?: throw RuntimeException("FragmentFirstBinding == null")

    private lateinit var viewModel: GifsViewModel
    private val rvAdapter by lazy {
        GifsAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[GifsViewModel::class.java]
        setupRecyclerView()
        viewModel.gifList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvGifs) {
            adapter = rvAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    viewModel.loadGifs()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}