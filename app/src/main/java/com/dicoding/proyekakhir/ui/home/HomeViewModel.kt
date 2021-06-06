package com.dicoding.proyekakhir.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.data.remote.repository.Repository
import com.dicoding.proyekakhir.vo.Resource

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> = repository.loadMovies()

    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> = repository.loadTvShows()

}