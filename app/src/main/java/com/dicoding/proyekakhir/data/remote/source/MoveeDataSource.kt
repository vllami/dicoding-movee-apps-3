package com.dicoding.proyekakhir.data.remote.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.vo.Resource

interface MoveeDataSource {

    fun loadMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun loadTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>>

    fun loadMoviesDetails(moviesID: Int): LiveData<Resource<MoviesEntity>>

    fun loadTvShowsDetails(tvShowsID: Int): LiveData<Resource<TvShowsEntity>>

    fun setMoviesWatchlist(movies: MoviesEntity, state: Boolean)

    fun setTvShowsWatchlist(tvShows: TvShowsEntity, state: Boolean)

    fun getMoviesWatchlist(): LiveData<PagedList<MoviesEntity>>

    fun getTvShowsWatchlist(): LiveData<PagedList<TvShowsEntity>>

}