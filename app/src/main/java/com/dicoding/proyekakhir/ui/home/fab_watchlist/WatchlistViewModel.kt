package com.dicoding.proyekakhir.ui.home.fab_watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.data.remote.repository.Repository

class WatchlistViewModel(private val repository: Repository) : ViewModel() {

    fun getMoviesWatchlist(): LiveData<PagedList<MoviesEntity>> = repository.getMoviesWatchlist()

    fun setMoviesWatchlist(entityOfMovies: MoviesEntity) {
        !entityOfMovies.addWatchlist.also {
            repository.setMoviesWatchlist(entityOfMovies, it)
        }
    }

    fun getTvShowsWatchlist(): LiveData<PagedList<TvShowsEntity>> = repository.getTvShowsWatchlist()

    fun setTvShowsWatchlist(entityOfTvShows: TvShowsEntity) {
        !entityOfTvShows.addWatchlist.also {
            repository.setTvShowsWatchlist(entityOfTvShows, it)
        }
    }

}