package com.dicoding.proyekakhir.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.repository.MoveeRepository
import com.dicoding.proyekakhir.core.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moveeRepository: MoveeRepository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var tvShowsObserver: Observer<Resource<PagedList<TvShowsEntity>>>

    @Mock
    private lateinit var moviesPagedList: PagedList<MoviesEntity>

    @Mock
    private lateinit var tvShowsPagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(moveeRepository)
    }

    @Test
    fun movies() {
        val resource = Resource.success(moviesPagedList)

        `when`(resource.data?.size).thenReturn(3)

        MutableLiveData<Resource<PagedList<MoviesEntity>>>().also {
            it.value = resource
            `when`(moveeRepository.loadMovies()).thenReturn(it)
        }

        mainViewModel.getMovies().apply {
            value?.data.also {
                verify(moveeRepository).loadMovies()
                assertThat(it).isNotNull()
                assertThat(it?.size).isEqualTo(3)
            }

            observeForever(moviesObserver)
            verify(moviesObserver).onChanged(resource)
        }
    }

    @Test
    fun tvShows() {
        val resource = Resource.success(tvShowsPagedList)

        `when`(resource.data?.size).thenReturn(3)

        MutableLiveData<Resource<PagedList<TvShowsEntity>>>().also {
            it.value = resource
            `when`(moveeRepository.loadTvShows()).thenReturn(it)
        }

        mainViewModel.getTvShows().apply {
            value?.data.also {
                verify(moveeRepository).loadTvShows()
                assertThat(it).isNotNull()
                assertThat(it?.size).isEqualTo(3)
            }

            observeForever(tvShowsObserver)
            verify(tvShowsObserver).onChanged(resource)
        }
    }
}