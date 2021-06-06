@file:Suppress("DEPRECATION")

package com.dicoding.proyekakhir.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.proyekakhir.data.NetworkBoundResource
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.data.remote.response.APIResponse
import com.dicoding.proyekakhir.data.remote.response.movies.MoviesDetailsResponse
import com.dicoding.proyekakhir.data.remote.response.movies.MoviesRemote
import com.dicoding.proyekakhir.data.remote.response.tv_shows.TvShowsDetailsResponse
import com.dicoding.proyekakhir.data.remote.response.tv_shows.TvShowsRemote
import com.dicoding.proyekakhir.data.remote.source.MoveeDataSource
import com.dicoding.proyekakhir.data.remote.source.LocalDataSource
import com.dicoding.proyekakhir.data.remote.source.RemoteDataSource
import com.dicoding.proyekakhir.utils.AppExecutors
import com.dicoding.proyekakhir.vo.Resource

class Repository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) : MoveeDataSource {

    override fun loadMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<MoviesRemote>>(appExecutors) {
            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMoviesData(), config).build()
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
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShowsData(), config).build()
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
                return localDataSource.getMoviesById(moviesID)
            }

            override fun createCall(): LiveData<APIResponse<MoviesDetailsResponse>> {
                return remoteDataSource.getMoviesDetails(moviesID.toString())
            }

            override fun saveCallResult(data: MoviesDetailsResponse) {
                data.apply {
                    val moviesEntity = MoviesEntity(
                        id = id,
                        backdrop = backdrop,
                        poster = poster,
                        title = title,
                        rating = rating,
                        releaseDate = releaseDate,
                        synopsis = synopsis,
                        addWatchlist = false
                    )

                    localDataSource.updateMoviesWatchlist(moviesEntity, false)
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
                return localDataSource.getTvShowsById(tvShowsID)
            }

            override fun createCall(): LiveData<APIResponse<TvShowsDetailsResponse>> {
                return remoteDataSource.getTvShowsDetails(tvShowsID.toString())
            }

            override fun saveCallResult(data: TvShowsDetailsResponse) {
                data.apply {
                    val tvShowsEntity = TvShowsEntity(
                        id = id,
                        backdrop = backdrop,
                        poster = poster,
                        title = title,
                        rating = rating,
                        releaseDate = releaseDate,
                        synopsis = synopsis,
                        addWatchlist = false
                    )

                    localDataSource.updateTvShowsWatchlist(tvShowsEntity, false)
                }
            }
        }.asLiveData()
    }

    override fun setMoviesWatchlist(movies: MoviesEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.updateMoviesWatchlist(movies, state)
        }
    }

    override fun setTvShowsWatchlist(tvShows: TvShowsEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.updateTvShowsWatchlist(tvShows, state)
        }
    }

    override fun getMoviesWatchlist(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getMoviesWatchList(), config).build()
    }

    override fun getTvShowsWatchlist(): LiveData<PagedList<TvShowsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getTvShowsWatchList(), config).build()
    }

    companion object {
        @Volatile
        private var repository: Repository? = null

        fun getRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): Repository {
            return repository ?: synchronized(this) {
                Repository(remoteDataSource, localDataSource, appExecutors).apply {
                    repository = this
                }
            }
        }
    }

}