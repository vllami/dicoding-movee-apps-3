package com.dicoding.proyekakhir.ui.home.tab_tv_shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.proyekakhir.databinding.FragmentTvShowsBinding
import com.dicoding.proyekakhir.databinding.FragmentTvShowsBinding.inflate
import com.dicoding.proyekakhir.ui.home.HomeViewModel
import com.dicoding.proyekakhir.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import com.dicoding.proyekakhir.vo.Status
import androidx.lifecycle.ViewModelProvider as HomeViewModelProvider

class TvShowsFragment : Fragment() {

    private lateinit var tvShowsAdapter: TvShowsAdapter
    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowsBinding = inflate(layoutInflater, container, false)

        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            tvShowsAdapter = TvShowsAdapter()

            HomeViewModelProvider(this, getViewModelFactory(requireActivity()))[HomeViewModel::class.java].also {
                it.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                    if (tvShows != null) {
                        when (tvShows.status) {
                            Status.LOADING -> {
                                showControl(true)
                            }
                            Status.SUCCESS -> {
                                showControl(false)
                                tvShowsAdapter.submitList(tvShows.data)
                            }
                            Status.ERROR -> {
                                showControl(false)
                                Toast.makeText(context, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }

            fragmentTvShowsBinding.rvTvShows.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    private fun showControl(state: Boolean) {
        fragmentTvShowsBinding.apply {
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