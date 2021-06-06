package com.dicoding.proyekakhir.data.remote.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.data.room.MoveeDAO

class LocalDataSource private constructor(private val moveeDAO: MoveeDAO) {

    companion object {
        private var localDataSource: LocalDataSource? = null

        fun getLocalDataSource(moveeDAO: MoveeDAO): LocalDataSource {
            return localDataSource ?: LocalDataSource(moveeDAO).apply {
                localDataSource = this
            }
        }
    }

    fun getAllMoviesData(): DataSource.Factory<Int, MoviesEntity> = moveeDAO.getMovies()

    fun getAllTvShowsData(): DataSource.Factory<Int, TvShowsEntity> = moveeDAO.getTvShows()

    fun getMoviesWatchList(): DataSource.Factory<Int, MoviesEntity> = moveeDAO.getMoviesWatchlist()

    fun getTvShowsWatchList(): DataSource.Factory<Int, TvShowsEntity> = moveeDAO.getTvShowsWatchlist()

    fun getMoviesById(moviesID: Int): LiveData<MoviesEntity> = moveeDAO.getMoviesById(moviesID)

    fun getTvShowsById(tvShowsId: Int): LiveData<TvShowsEntity> = moveeDAO.getTvShowsById(tvShowsId)

    fun insertMovies(movies: List<MoviesEntity>) = moveeDAO.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowsEntity>) = moveeDAO.insertTvShows(tvShows)

    fun updateMoviesWatchlist(movies: MoviesEntity, newState: Boolean) {
        movies.addWatchlist = newState
        moveeDAO.updateMovies(movies)
    }

    fun updateTvShowsWatchlist(tvShows: TvShowsEntity, newState: Boolean) {
        tvShows.addWatchlist = newState
        moveeDAO.updateTvShows(tvShows)
    }
}