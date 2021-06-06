package com.dicoding.proyekakhir.ui.home.fab_watchlist.tab_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.proyekakhir.databinding.FragmentMoviesWatchlistBinding
import com.dicoding.proyekakhir.ui.home.fab_watchlist.WatchlistViewModel
import com.dicoding.proyekakhir.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MoviesWatchlistFragment : Fragment() {

    private lateinit var fragmentMoviesWatchlistBinding: FragmentMoviesWatchlistBinding
    private lateinit var watchlistViewModel: WatchlistViewModel
    private lateinit var moviesWatchlistAdapter: MoviesWatchlistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMoviesWatchlistBinding = FragmentMoviesWatchlistBinding.inflate(layoutInflater, container, false)

        return fragmentMoviesWatchlistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(fragmentMoviesWatchlistBinding.rvMovies)

        if (activity != null) {
            showControl(true)

            moviesWatchlistAdapter = MoviesWatchlistAdapter()
            ViewModelProvider(this, ViewModelFactory.getViewModelFactory(requireActivity()))[WatchlistViewModel::class.java].also { watchlistViewModel ->
                watchlistViewModel.getMoviesWatchlist().observe(viewLifecycleOwner, {
                    showControl(false)

                    moviesWatchlistAdapter.submitList(it)
                })
            }

            fragmentMoviesWatchlistBinding.rvMovies.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = moviesWatchlistAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return makeMovementFlags(0, ItemTouchHelper.RIGHT)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val moviesSwiped = moviesWatchlistAdapter.getSwiped(viewHolder.bindingAdapterPosition)
                moviesSwiped?.let {
                    watchlistViewModel.setMoviesWatchlist(it)
                }

                Snackbar.make(view as View, "Deleted from Watchlist", Snackbar.LENGTH_LONG).also {
                    it.setAction("Undo") {
                        moviesSwiped?.let {
                            watchlistViewModel.setMoviesWatchlist(it)
                        }
                    }
                }.show()
            }
        }
    })

    private fun showControl(state: Boolean) {
        fragmentMoviesWatchlistBinding.apply {
            when {
                state -> {
                    rvMovies.visibility = View.GONE
                    loading.visibility = View.VISIBLE
                }
                else -> {
                    rvMovies.visibility = View.VISIBLE
                    loading.visibility = View.GONE
                }
            }
        }
    }

}