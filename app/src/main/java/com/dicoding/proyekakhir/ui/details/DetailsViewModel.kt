package com.dicoding.proyekakhir.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.data.remote.repository.Repository
import com.dicoding.proyekakhir.vo.Resource

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    private lateinit var moviesEntity: LiveData<Resource<MoviesEntity>>
    private lateinit var tvShowsEntity: LiveData<Resource<TvShowsEntity>>

    fun setMoviesData(id: Int) : LiveData<Resource<MoviesEntity>> {
        repository.loadMoviesDetails(id).also {
            return it
        }
    }

    fun setTvShowsData(id: Int) : LiveData<Resource<TvShowsEntity>> {
        repository.loadTvShowsDetails(id).also {
            return it
        }
    }

    fun setMoviesWatchlist() {
        moviesEntity.value.also { moviesEntity ->
            if (moviesEntity?.data != null) {
                !moviesEntity.data.addWatchlist.also {
                    repository.setMoviesWatchlist(moviesEntity.data, it)
                }
            }
        }
    }

    fun setTvShowsWatchlist() {
        tvShowsEntity.value.also { tvShowsEntity ->
            if (tvShowsEntity?.data != null) {
                !tvShowsEntity.data.addWatchlist.also {
                    repository.setTvShowsWatchlist(tvShowsEntity.data, it)
                }
            }
        }
    }

}