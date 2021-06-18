package com.dicoding.proyekakhir.ui.main.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.repository.MoveeRepository

class WatchlistViewModel(private val repository: MoveeRepository) : ViewModel() {

    fun getMoviesWatchlist(): LiveData<PagedList<MoviesEntity>> = repository.getMoviesWatchlist()

    fun setMoviesWatchlist(moviesEntity: MoviesEntity) {
        moviesEntity.also {
            repository.setMoviesWatchlist(it, !it.watchlist)
        }
    }

    fun getTvShowsWatchlist(): LiveData<PagedList<TvShowsEntity>> = repository.getTvShowsWatchlist()

    fun setTvShowsWatchlist(tvShowsEntity: TvShowsEntity) {
        tvShowsEntity.also {
            repository.setTvShowsWatchlist(it, !it.watchlist)
        }
    }

}