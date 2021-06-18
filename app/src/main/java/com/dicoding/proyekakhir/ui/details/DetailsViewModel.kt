package com.dicoding.proyekakhir.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.repository.MoveeRepository
import com.dicoding.proyekakhir.core.utils.Resource

class DetailsViewModel(private val moveeRepository: MoveeRepository) : ViewModel() {

    private lateinit var moviesEntity: LiveData<Resource<MoviesEntity>>
    private lateinit var tvShowsEntity: LiveData<Resource<TvShowsEntity>>

    fun setMoviesData(moviesID: Int) : LiveData<Resource<MoviesEntity>> {
        moviesEntity = moveeRepository.loadMoviesDetails(moviesID)

        return moviesEntity
    }

    fun setTvShowsData(tvShowsID: Int) : LiveData<Resource<TvShowsEntity>> {
        tvShowsEntity = moveeRepository.loadTvShowsDetails(tvShowsID)

        return tvShowsEntity
    }

    fun setMoviesWatchlist() {
        moviesEntity.value.also {
            if (it?.data != null) {
                it.data.apply {
                    moveeRepository.setMoviesWatchlist(this, !this.watchlist)
                }
            }
        }
    }

    fun setTvShowsWatchlist() {
        tvShowsEntity.value.also {
            if (it?.data != null) {
                it.data.apply {
                    moveeRepository.setTvShowsWatchlist(this, !this.watchlist)
                }
            }
        }
    }

}

