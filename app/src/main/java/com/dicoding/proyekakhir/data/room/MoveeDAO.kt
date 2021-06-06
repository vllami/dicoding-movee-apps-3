package com.dicoding.proyekakhir.data.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity

@Dao
interface MoveeDAO {

    @Query("SELECT * FROM movies_entity")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tv_shows_entity")
    fun getTvShows(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM movies_entity WHERE add_watchlist = 1")
    fun getMoviesWatchlist(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE add_watchlist = 1")
    fun getTvShowsWatchlist(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM movies_entity WHERE id = :id")
    fun getMoviesById(id: Int): LiveData<MoviesEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE id = :id")
    fun getTvShowsById(id: Int): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShow: List<TvShowsEntity>)

    @Update
    fun updateMovies(movies: MoviesEntity)

    @Update
    fun updateTvShows(tvShow: TvShowsEntity)

}