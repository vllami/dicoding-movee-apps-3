package com.dicoding.proyekakhir.ui.home.fab_watchlist.tab_tv_shows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.proyekakhir.R
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.databinding.FragmentTvShowsWatchlistBinding
import com.dicoding.proyekakhir.ui.home.fab_watchlist.WatchlistViewModel
import com.dicoding.proyekakhir.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class TvShowsWatchlistFragment : Fragment() {

    private lateinit var fragmentTvShowsWatchlistBinding: FragmentTvShowsWatchlistBinding
    private lateinit var watchlistViewModel: WatchlistViewModel
    private lateinit var tvShowsWatchlistAdapter: TvShowsWatchlistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvShowsWatchlistBinding = FragmentTvShowsWatchlistBinding.inflate(layoutInflater, container, false)

        return fragmentTvShowsWatchlistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(fragmentTvShowsWatchlistBinding.rvTvShows)

        if (activity != null) {
            showControl(true)

            tvShowsWatchlistAdapter = TvShowsWatchlistAdapter()
            ViewModelProvider(this, ViewModelFactory.getViewModelFactory(requireActivity()))[WatchlistViewModel::class.java].also { watchlistViewModel ->
                watchlistViewModel.getTvShowsWatchlist().observe(viewLifecycleOwner, {
                    showControl(false)

                    tvShowsWatchlistAdapter.submitList(it)
                })
            }

            fragmentTvShowsWatchlistBinding.rvTvShows.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = tvShowsWatchlistAdapter
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
                val tvShowsSwiped = tvShowsWatchlistAdapter.getSwiped(viewHolder.bindingAdapterPosition)
                tvShowsSwiped?.let {
                    watchlistViewModel.setTvShowsWatchlist(it)
                }

                Snackbar.make(view as View, "Deleted from Watchlist", Snackbar.LENGTH_LONG).also {
                    it.setAction("Undo") {
                        tvShowsSwiped?.let {
                            watchlistViewModel.setTvShowsWatchlist(it)
                        }
                    }
                }.show()
            }
        }
    })

    private fun showControl(state: Boolean) {
        fragmentTvShowsWatchlistBinding.apply {
            when {
                state -> {
                    rvTvShows.visibility = View.GONE
                    loading.visibility = View.VISIBLE
                }
                else -> {
                    rvTvShows.visibility = View.VISIBLE
                    loading.visibility = View.GONE
                }
            }
        }
    }

}