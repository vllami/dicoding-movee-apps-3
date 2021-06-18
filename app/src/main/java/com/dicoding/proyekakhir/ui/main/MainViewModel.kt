package com.dicoding.proyekakhir.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.repository.MoveeRepository
import com.dicoding.proyekakhir.core.utils.Resource

class MainViewModel(private val repository: MoveeRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> = repository.loadMovies()

    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> = repository.loadTvShows()

}