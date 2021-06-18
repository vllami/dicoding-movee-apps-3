@file:Suppress("DEPRECATION")

package com.dicoding.proyekakhir.core.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.proyekakhir.core.NetworkBoundResource
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.model.remote.movies.MoviesRemote
import com.dicoding.proyekakhir.core.model.remote.tv_shows.TvShowsRemote
import com.dicoding.proyekakhir.core.model.response.APIResponse
import com.dicoding.proyekakhir.core.model.response.movies.MoviesDetailsResponse
import com.dicoding.proyekakhir.core.model.response.tv_shows.TvShowsDetailsResponse
import com.dicoding.proyekakhir.core.source.LocalDataSource
import com.dicoding.proyekakhir.core.source.MoveeDataSource
import com.dicoding.proyekakhir.core.source.RemoteDataSource
import com.dicoding.proyekakhir.core.utils.AppExecutors
import com.dicoding.proyekakhir.core.utils.Resource

class FakeMoveeRepository constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) : MoveeDataSource {

    override fun loadMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<MoviesRemote>>(appExecutors) {
            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build().also {
                        return LivePagedListBuilder(localDataSource.getMovies(), it).build()
                    }
            }

            override fun createCall(): LiveData<APIResponse<List<MoviesRemote>>> {
                return remoteDataSource.getMoviesList()
            }

            override fun saveCallResult(data: List<MoviesRemote>) {
                val moviesList = ArrayList<MoviesEntity>()

                for (moviesData in data) {
                    moviesData.apply {
                        MoviesEntity(
                            id,
                            backdrop,
                            poster,
                            title,
                            rating,
                            releaseDate,
                            synopsis,
                            false
                        ).also {
                            moviesList.add(it)
                        }
                    }
                }
                localDataSource.insertMovies(moviesList)
            }
        }.asLiveData()
    }

    override fun loadTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, List<TvShowsRemote>>(appExecutors) {
            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build().also {
                        return LivePagedListBuilder(localDataSource.getTvShows(), it).build()
                    }
            }

            override fun createCall(): LiveData<APIResponse<List<TvShowsRemote>>> {
                return remoteDataSource.getTvShowsList()
            }

            override fun saveCallResult(data: List<TvShowsRemote>) {
                val tvShowsList = ArrayList<TvShowsEntity>()

                for (tvShowsData in data) {
                    tvShowsData.apply {
                        TvShowsEntity(
                            id,
                            backdrop,
                            poster,
                            title,
                            rating,
                            releaseDate,
                            synopsis,
                            false
                        ).also {
                            tvShowsList.add(it)
                        }
                    }
                }
                localDataSource.insertTvShows(tvShowsList)
            }
        }.asLiveData()
    }

    override fun loadMoviesDetails(moviesID: Int): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesDetailsResponse>(appExecutors) {
            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return data == null
            }

            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localDataSource.getMoviesByID(moviesID)
            }

            override fun createCall(): LiveData<APIResponse<MoviesDetailsResponse>> {
                return remoteDataSource.getMoviesDetails(moviesID.toString())
            }

            override fun saveCallResult(data: MoviesDetailsResponse) {
                data.apply {
                    MoviesEntity(
                        id = this.id,
                        backdrop = backdrop,
                        poster = poster,
                        title = title,
                        rating = rating,
                        releaseDate = releaseDate,
                        synopsis = synopsis,
                        watchlist = false
                    ).also {
                        localDataSource.updateMoviesWatchlist(it, false)
                    }
                }
            }
        }.asLiveData()
    }

    override fun loadTvShowsDetails(tvShowsID: Int): LiveData<Resource<TvShowsEntity>> {
        return object : NetworkBoundResource<TvShowsEntity, TvShowsDetailsResponse>(appExecutors) {
            override fun shouldFetch(data: TvShowsEntity?): Boolean {
                return data == null
            }

            override fun loadFromDB(): LiveData<TvShowsEntity> {
                return localDataSource.getTvShowsByID(tvShowsID)
            }

            override fun createCall(): LiveData<APIResponse<TvShowsDetailsResponse>> {
                return remoteDataSource.getTvShowsDetails(tvShowsID.toString())
            }

            override fun saveCallResult(data: TvShowsDetailsResponse) {
                data.apply {
                    TvShowsEntity(
                        id = this.id,
                        backdrop = backdrop,
                        poster = poster,
                        title = title,
                        rating = rating,
                        releaseDate = releaseDate,
                        synopsis = synopsis,
                        watchlist = false
                    ).also {
                        localDataSource.updateTvShowsWatchlist(it, false)
                    }
                }
            }
        }.asLiveData()
    }

    override fun setMoviesWatchlist(moviesEntity: MoviesEntity, state: Boolean) {
        localDataSource.updateMoviesWatchlist(moviesEntity, state)
    }

    override fun setTvShowsWatchlist(tvShowsEntity: TvShowsEntity, state: Boolean) {
        localDataSource.updateTvShowsWatchlist(tvShowsEntity, state)
    }

    override fun getMoviesWatchlist(): LiveData<PagedList<MoviesEntity>> {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build().also {
                return LivePagedListBuilder(localDataSource.getMoviesWatchList(), it).build()
            }
    }

    override fun getTvShowsWatchlist(): LiveData<PagedList<TvShowsEntity>> {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build().also {
                return LivePagedListBuilder(localDataSource.getTvShowsWatchList(), it).build()
            }
    }

}