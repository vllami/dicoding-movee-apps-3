package com.dicoding.proyekakhir.ui.main.tv_shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.proyekakhir.R.string
import com.dicoding.proyekakhir.core.enums.Status.*
import com.dicoding.proyekakhir.core.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import com.dicoding.proyekakhir.databinding.FragmentTvShowsBinding
import com.dicoding.proyekakhir.databinding.FragmentTvShowsBinding.inflate
import com.dicoding.proyekakhir.ui.main.MainViewModel
import android.widget.Toast.makeText as Toast
import androidx.lifecycle.ViewModelProvider as MainViewModelProvider

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

            MainViewModelProvider(this, getViewModelFactory(requireActivity()))[MainViewModel::class.java].also {
                it.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                    tvShows.apply {
                        if (this != null) {
                            when (status) {
                                LOADING -> {
                                    showLoading(true)
                                }
                                SUCCESS -> {
                                    showLoading(false)
                                    tvShowsAdapter.submitList(data)
                                }
                                ERROR -> {
                                    showLoading(false)
                                    Toast(context, resources.getString(string.failed_load), LENGTH_SHORT).show()
                                }
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

    private fun showLoading(state: Boolean) {
        fragmentTvShowsBinding.loading.visibility = if (state) VISIBLE else GONE
    }

}