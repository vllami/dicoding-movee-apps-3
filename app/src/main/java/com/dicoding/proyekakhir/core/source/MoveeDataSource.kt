package com.dicoding.proyekakhir.core.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.utils.Resource

interface MoveeDataSource {

    fun loadMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun loadTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>>

    fun loadMoviesDetails(moviesID: Int): LiveData<Resource<MoviesEntity>>

    fun loadTvShowsDetails(tvShowsID: Int): LiveData<Resource<TvShowsEntity>>

    fun setMoviesWatchlist(moviesEntity: MoviesEntity, state: Boolean)

    fun setTvShowsWatchlist(tvShowsEntity: TvShowsEntity, state: Boolean)

    fun getMoviesWatchlist(): LiveData<PagedList<MoviesEntity>>

    fun getTvShowsWatchlist(): LiveData<PagedList<TvShowsEntity>>

}