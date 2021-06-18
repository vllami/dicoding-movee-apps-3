package com.dicoding.proyekakhir.core.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.source.LocalDataSource
import com.dicoding.proyekakhir.core.source.RemoteDataSource
import com.dicoding.proyekakhir.core.utils.*
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class MoveeRepositoryTest {

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val fakeMoveeRepository = FakeMoveeRepository(remoteDataSource, localDataSource, appExecutors)

    private val moviesRemote = DummyData.getMoviesRemote()
    private val tvShowsRemote = DummyData.getTvShowsRemote()

    private val moviesID = moviesRemote[0].id
    private val tvShowsID = tvShowsRemote[0].id

    private val moviesDetailsResponse = DetailsDummyData.getMoviesDetailsRemote()
    private val tvShowsDetailsResponse = DetailsDummyData.getTvShowsDetailsRemote()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getMoviesList() {
        mock(DataSource.Factory::class.java).also {
            `when`(localDataSource.getMovies()).thenReturn(it as DataSource.Factory<Int, MoviesEntity>)
            fakeMoveeRepository.loadMovies()
        }

        Resource.success(PagedListUtils.mockingPagedList(DummyData.getMovies())).also {
            verify(localDataSource).getMovies()

            with(it.data) {
                assertThat(this).isNotNull()
                assertThat(this?.size?.toLong()).isEqualTo(moviesRemote.size.toLong())
            }
        }
    }

    @Test
    fun getTvShowsList() {
        mock(DataSource.Factory::class.java).also {
            `when`(localDataSource.getTvShows()).thenReturn(it as DataSource.Factory<Int, TvShowsEntity>)
            fakeMoveeRepository.loadTvShows()
        }

        Resource.success(PagedListUtils.mockingPagedList(DummyData.getTvShows())).also {
            verify(localDataSource).getTvShows()

            with(it.data) {
                assertThat(this).isNotNull()
                assertThat(this?.size?.toLong()).isEqualTo(tvShowsRemote.size.toLong())
            }
        }
    }

    @Test
    fun getMoviesDetails() {
        MutableLiveData<MoviesEntity>().also {
            it.value = DetailsDummyData.getMoviesDetails()
            `when`(localDataSource.getMoviesByID(moviesID)).thenReturn(it)
        }

        LiveDataTestUtils.getValue(fakeMoveeRepository.loadMoviesDetails(moviesID)).also {
            verify(localDataSource).getMoviesByID(moviesID)

            assertThat(it).isNotNull()
            assertThat(it.data?.id).isEqualTo(moviesDetailsResponse.id)
        }
    }

    @Test
    fun getTvShowsDetails() {
        MutableLiveData<TvShowsEntity>().also {
            it.value = DetailsDummyData.getTvShowsDetails()
            `when`(localDataSource.getTvShowsByID(tvShowsID)).thenReturn(it)
        }

        LiveDataTestUtils.getValue(fakeMoveeRepository.loadTvShowsDetails(tvShowsID)).also {
            verify(localDataSource).getTvShowsByID(tvShowsID)

            assertThat(it).isNotNull()
            assertThat(it.data?.id).isEqualTo(tvShowsDetailsResponse.id)
        }
    }

    @Test
    fun setWatchlist() {
        fakeMoveeRepository.setMoviesWatchlist(DetailsDummyData.getMoviesDetails(), true)

        verify(localDataSource).updateMoviesWatchlist(DetailsDummyData.getMoviesDetails(), true)
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun getWatchlist() {
        mock(DataSource.Factory::class.java).also {
            `when`(localDataSource.getMoviesWatchList()).thenReturn(it as DataSource.Factory<Int, MoviesEntity>)
            fakeMoveeRepository.getMoviesWatchlist()
        }

        Resource.success(PagedListUtils.mockingPagedList(DummyData.getMovies())).also {
            verify(localDataSource).getMoviesWatchList()

            assertThat(it).isNotNull()
            assertThat(it.data?.size).isEqualTo(moviesRemote.size)
        }
    }

}