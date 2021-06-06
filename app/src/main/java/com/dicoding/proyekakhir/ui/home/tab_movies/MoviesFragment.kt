package com.dicoding.proyekakhir.ui.home.tab_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.proyekakhir.databinding.FragmentMoviesBinding
import com.dicoding.proyekakhir.ui.home.HomeViewModel
import com.dicoding.proyekakhir.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import com.dicoding.proyekakhir.vo.Status
import androidx.lifecycle.ViewModelProvider as HomeViewModelProvider

class MoviesFragment : Fragment() {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)

        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            moviesAdapter = MoviesAdapter()

            HomeViewModelProvider(this, getViewModelFactory(requireActivity()))[HomeViewModel::class.java].also {
                it.getMovies().observe(viewLifecycleOwner, { movies ->
                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> {
                                showControl(true)
                            }
                            Status.SUCCESS -> {
                                showControl(false)
                                moviesAdapter.submitList(movies.data)
                            }
                            Status.ERROR -> {
                                showControl(false)
                                Toast.makeText(context, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }

            fragmentMoviesBinding.rvMovies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    private fun showControl(state: Boolean) {
        fragmentMoviesBinding.apply {
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