package com.dicoding.proyekakhir.ui.main.watchlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.repository.MoveeRepository
import com.dicoding.proyekakhir.core.utils.DetailsDummyData
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WatchlistViewModelTest {

    private lateinit var watchlistViewModel: WatchlistViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moveeRepository: MoveeRepository

    @Mock
    private lateinit var moviesObserver: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var tvShowsObserver: Observer<PagedList<TvShowsEntity>>

    @Mock
    private lateinit var moviesPagedList: PagedList<MoviesEntity>

    @Mock
    private lateinit var tvShowsPagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        watchlistViewModel = WatchlistViewModel(moveeRepository)
    }

    @Test
    fun settingMoviesWatchlist() {
        watchlistViewModel.setMoviesWatchlist(DetailsDummyData.getMoviesDetails())
        verify(moveeRepository).setMoviesWatchlist(DetailsDummyData.getMoviesDetails(), true)
        verifyNoMoreInteractions(moveeRepository)
    }

    @Test
    fun settingTvShowsWatchlist() {
        watchlistViewModel.setTvShowsWatchlist(DetailsDummyData.getTvShowsDetails())
        verify(moveeRepository).setTvShowsWatchlist(DetailsDummyData.getTvShowsDetails(), true)
        verifyNoMoreInteractions(moveeRepository)
    }

    @Test
    fun gettingMoviesWatchlist() {
        val pagedList = moviesPagedList

        `when`(pagedList.size).thenReturn(5)

        MutableLiveData<PagedList<MoviesEntity>>().also {
            it.value = pagedList
            `when`(moveeRepository.getMoviesWatchlist()).thenReturn(it)
        }

        watchlistViewModel.getMoviesWatchlist().apply {
            value.also {
                verify(moveeRepository).getMoviesWatchlist()
                assertThat(it).isNotNull()
                assertThat(it?.size).isEqualTo(5)
            }

            observeForever(moviesObserver)
            verify(moviesObserver).onChanged(pagedList)
        }
    }

    @Test
    fun gettingTvShowsWatchlist() {
        val pagedList = tvShowsPagedList

        `when`(pagedList.size).thenReturn(5)

        MutableLiveData<PagedList<TvShowsEntity>>().also {
            it.value = pagedList
            `when`(moveeRepository.getTvShowsWatchlist()).thenReturn(it)
        }

        watchlistViewModel.getTvShowsWatchlist().apply {
            value.also {
                verify(moveeRepository).getTvShowsWatchlist()
                assertThat(it).isNotNull()
                assertThat(it?.size).isEqualTo(5)
            }

            observeForever(tvShowsObserver)
            verify(tvShowsObserver).onChanged(pagedList)
        }
    }

}