package com.dicoding.proyekakhir.core.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.proyekakhir.core.local.MoveeDAO
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity

class LocalDataSource private constructor(private val moveeDAO: MoveeDAO) {

    fun getMovies(): DataSource.Factory<Int, MoviesEntity> = moveeDAO.movies()

    fun getTvShows(): DataSource.Factory<Int, TvShowsEntity> = moveeDAO.tvShows()

    fun getMoviesWatchList(): DataSource.Factory<Int, MoviesEntity> = moveeDAO.moviesWatchlist()

    fun getTvShowsWatchList(): DataSource.Factory<Int, TvShowsEntity> = moveeDAO.tvShowsWatchlist()

    fun getMoviesByID(id: Int): LiveData<MoviesEntity> = moveeDAO.moviesID(id)

    fun getTvShowsByID(id: Int): LiveData<TvShowsEntity> = moveeDAO.tvShowsID(id)

    fun insertMovies(moviesEntity: List<MoviesEntity>) = moveeDAO.insertMovies(moviesEntity)

    fun insertTvShows(tvShowsEntity: List<TvShowsEntity>) = moveeDAO.insertTvShows(tvShowsEntity)

    fun updateMoviesWatchlist(moviesEntity: MoviesEntity, newState: Boolean) {
        moviesEntity.apply {
            watchlist = newState
            moveeDAO.updateMovies(this)
        }
    }

    fun updateTvShowsWatchlist(tvShowsEntity: TvShowsEntity, newState: Boolean) {
        tvShowsEntity.apply {
            watchlist = newState
            moveeDAO.updateTvShows(this)
        }
    }

    companion object {
        private var localDataSource: LocalDataSource? = null

        fun getLocalDataSource(moveeDAO: MoveeDAO): LocalDataSource {
            return localDataSource ?: LocalDataSource(moveeDAO).apply {
                localDataSource = this
            }
        }
    }

}