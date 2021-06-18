package com.dicoding.proyekakhir.ui.main.watchlist.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.proyekakhir.core.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import com.dicoding.proyekakhir.databinding.FragmentMoviesWatchlistBinding
import com.dicoding.proyekakhir.databinding.FragmentMoviesWatchlistBinding.inflate
import com.dicoding.proyekakhir.ui.main.movies.MoviesAdapter
import com.dicoding.proyekakhir.ui.main.watchlist.WatchlistViewModel

class MoviesWatchlistFragment : Fragment() {

    private lateinit var fragmentMoviesWatchlistBinding: FragmentMoviesWatchlistBinding
    private lateinit var watchlistViewModel: WatchlistViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesWatchlistBinding = inflate(layoutInflater, container, false)

        return fragmentMoviesWatchlistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showLoading(true)

            moviesAdapter = MoviesAdapter()

            watchlistViewModel = ViewModelProvider(this, getViewModelFactory(requireActivity()))[WatchlistViewModel::class.java]
            watchlistViewModel.getMoviesWatchlist().observe(viewLifecycleOwner, {
                showLoading(false)

                moviesAdapter.submitList(it)
            })

            fragmentMoviesWatchlistBinding.rvMovies.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = moviesAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        fragmentMoviesWatchlistBinding.loading.visibility = if (state) VISIBLE else GONE
    }

}