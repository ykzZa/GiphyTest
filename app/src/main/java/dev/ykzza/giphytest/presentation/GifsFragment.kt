package dev.ykzza.giphytest.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.ykzza.giphytest.R
import dev.ykzza.giphytest.databinding.FragmentGifsBinding
import dev.ykzza.giphytest.domain.Gif

@AndroidEntryPoint
class GifsFragment : Fragment(), MenuProvider, GifsAdapter.OnItemClickListener {

    private var _binding: FragmentGifsBinding? = null
    private val binding
        get() =
            _binding ?: throw RuntimeException("FragmentFirstBinding == null")

    private lateinit var viewModel: GifsViewModel
    private val rvAdapter by lazy {
        GifsAdapter(this)
    }

    private var layoutManagerType = TYPE_GRID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifsBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[GifsViewModel::class.java]
        layoutManagerType = savedInstanceState?.getInt(LAYOUT_MANAGER_KEY) ?: TYPE_GRID
        setupRecyclerView()
        viewModel.gifList.observe(viewLifecycleOwner) {
            if(it.isNullOrEmpty()) {
                binding.ivNoInternet.visibility = View.VISIBLE
            } else {
                rvAdapter.submitList(it)
                binding.ivNoInternet.visibility = View.INVISIBLE
            }
        }
        handleInternetAvailable()
    }

    private fun handleInternetAvailable() {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                viewModel.loadGifs()
            }
        }
        connectivityManager.registerDefaultNetworkCallback(callback)
    }

    private fun setupRecyclerView() {
        with(binding.rvGifs) {
            adapter = rvAdapter
            layoutManager = when(layoutManagerType) {
                TYPE_GRID -> GridLayoutManager(requireContext(), 3)
                TYPE_LINEAR -> LinearLayoutManager(requireContext())
                else -> throw RuntimeException("Unknown layout manager type")
            }
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

    override fun onItemClick(gif: Gif) {
        val action = GifsFragmentDirections.actionGifsFragmentToGifDetailFragment(gif.originalUrl)
        findNavController().navigate(action)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.gif_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.type_grid -> {
                binding.rvGifs.layoutManager = GridLayoutManager(requireContext(), 3)
                layoutManagerType = TYPE_GRID
            }

            R.id.type_linear -> {
                binding.rvGifs.layoutManager = LinearLayoutManager(requireContext())
                layoutManagerType = TYPE_LINEAR
            }
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LAYOUT_MANAGER_KEY, layoutManagerType)
    }
    companion object {
        const val TYPE_GRID = 101
        const val TYPE_LINEAR = 100
        const val LAYOUT_MANAGER_KEY = "layout_manager_key"
    }
}