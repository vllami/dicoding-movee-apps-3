package com.dicoding.proyekakhir.ui.main.movies

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
import com.dicoding.proyekakhir.databinding.FragmentMoviesBinding
import com.dicoding.proyekakhir.databinding.FragmentMoviesBinding.inflate
import com.dicoding.proyekakhir.ui.main.MainViewModel
import android.widget.Toast.makeText as Toast
import androidx.lifecycle.ViewModelProvider as MainViewModelProvider

class MoviesFragment : Fragment() {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = inflate(layoutInflater, container, false)

        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            moviesAdapter = MoviesAdapter()

            MainViewModelProvider(this, getViewModelFactory(requireActivity()))[MainViewModel::class.java].also {
                it.getMovies().observe(viewLifecycleOwner, { movies ->
                        if (movies != null) {
                            when (movies.status) {
                                LOADING -> {
                                    showLoading(true)
                                }
                                SUCCESS -> {
                                    showLoading(false)
                                    moviesAdapter.submitList(movies.data)
                                }
                                ERROR -> {
                                    showLoading(false)
                                    Toast(context, resources.getString(string.failed_load), LENGTH_SHORT).show()
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

    private fun showLoading(state: Boolean) {
        fragmentMoviesBinding.loading.visibility = if (state) VISIBLE else GONE
    }

}