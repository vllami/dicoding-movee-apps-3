package com.dicoding.proyekakhir.ui.main.watchlist.tv_shows

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
import com.dicoding.proyekakhir.databinding.FragmentTvShowsWatchlistBinding
import com.dicoding.proyekakhir.databinding.FragmentTvShowsWatchlistBinding.inflate
import com.dicoding.proyekakhir.ui.main.tv_shows.TvShowsAdapter
import com.dicoding.proyekakhir.ui.main.watchlist.WatchlistViewModel

class TvShowsWatchlistFragment : Fragment() {

    private lateinit var fragmentTvShowsWatchlistBinding: FragmentTvShowsWatchlistBinding
    private lateinit var watchlistViewModel: WatchlistViewModel
    private lateinit var tvShowsAdapter: TvShowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowsWatchlistBinding = inflate(layoutInflater, container, false)

        return fragmentTvShowsWatchlistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showLoading(true)

            tvShowsAdapter = TvShowsAdapter()

            watchlistViewModel = ViewModelProvider(this, getViewModelFactory(requireActivity()))[WatchlistViewModel::class.java]
            watchlistViewModel.getTvShowsWatchlist().observe(viewLifecycleOwner, {
                showLoading(false)

                tvShowsAdapter.submitList(it)
            })

            fragmentTvShowsWatchlistBinding.rvTvShows.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = tvShowsAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        fragmentTvShowsWatchlistBinding.loading.visibility = if (state) VISIBLE else GONE
    }

}