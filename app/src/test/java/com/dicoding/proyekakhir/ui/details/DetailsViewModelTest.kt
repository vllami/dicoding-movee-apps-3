package com.dicoding.proyekakhir.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.repository.MoveeRepository
import com.dicoding.proyekakhir.core.utils.DetailsDummyData
import com.dicoding.proyekakhir.core.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {

    private lateinit var detailsViewModel: DetailsViewModel

    private val moviesDetails = DetailsDummyData.getMoviesDetails()
    private val tvShowsDetails = DetailsDummyData.getTvShowsDetails()

    private val moviesID = moviesDetails.id
    private val tvShowsID = tvShowsDetails.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moveeRepository: MoveeRepository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<MoviesEntity>>

    @Mock
    private lateinit var tvShowsObserver: Observer<Resource<TvShowsEntity>>

    @Before
    fun setUp() {
        detailsViewModel = DetailsViewModel(moveeRepository)
    }

    @Test
    fun getMoviesDetails() {
        MutableLiveData<Resource<MoviesEntity>>().also {
            it.value = Resource.success(DetailsDummyData.getMoviesDetails())
            `when`(moveeRepository.loadMoviesDetails(moviesID)).thenReturn(it)

            detailsViewModel.setMoviesData(moviesID).observeForever(moviesObserver)
            verify(moviesObserver).onChanged(it.value)
        }
    }

    @Test
    fun getTvShowsDetails() {
        MutableLiveData<Resource<TvShowsEntity>>().also {
            it.value = Resource.success(DetailsDummyData.getTvShowsDetails())
            `when`(moveeRepository.loadTvShowsDetails(tvShowsID)).thenReturn(it)

            detailsViewModel.setTvShowsData(tvShowsID).observeForever(tvShowsObserver)
            verify(tvShowsObserver).onChanged(it.value)
        }
    }

    @Test
    fun setMoviesWatchlist() {
        MutableLiveData<Resource<MoviesEntity>>().also {
            it.value = Resource.success(DetailsDummyData.getMoviesDetails())
            `when`(moveeRepository.loadMoviesDetails(moviesID)).thenReturn(it)

            detailsViewModel.apply {
                setMoviesData(moviesID).observeForever(moviesObserver)
                setMoviesWatchlist()
            }
            verify(moveeRepository).setMoviesWatchlist((it.value?.data) as MoviesEntity, true)
        }
    }

    @Test
    fun setTvShowsWatchlist() {
        MutableLiveData<Resource<TvShowsEntity>>().also {
            it.value = Resource.success(DetailsDummyData.getTvShowsDetails())
            `when`(moveeRepository.loadTvShowsDetails(tvShowsID)).thenReturn(it)

            detailsViewModel.apply {
                setTvShowsData(tvShowsID).observeForever(tvShowsObserver)
                setTvShowsWatchlist()
            }
            verify(moveeRepository).setTvShowsWatchlist((it.value?.data) as TvShowsEntity, true)
        }
    }
}