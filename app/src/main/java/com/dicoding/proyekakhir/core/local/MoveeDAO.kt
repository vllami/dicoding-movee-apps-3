package com.dicoding.proyekakhir.core.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity

@Dao
interface MoveeDAO {

    @Query("SELECT * FROM movies_entity")
    fun movies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tv_shows_entity")
    fun tvShows(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM movies_entity WHERE watchlist = 1")
    fun moviesWatchlist(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE watchlist = 1")
    fun tvShowsWatchlist(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM movies_entity WHERE id = :id")
    fun moviesID(id: Int): LiveData<MoviesEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE id = :id")
    fun tvShowsID(id: Int): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowsEntity>)

    @Update
    fun updateMovies(movies: MoviesEntity)

    @Update
    fun updateTvShows(tvShows: TvShowsEntity)

}