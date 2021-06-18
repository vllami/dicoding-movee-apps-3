package com.dicoding.proyekakhir.core.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.proyekakhir.core.Constant.PAGE
import com.dicoding.proyekakhir.core.model.remote.movies.MoviesRemote
import com.dicoding.proyekakhir.core.model.remote.tv_shows.TvShowsRemote
import com.dicoding.proyekakhir.core.model.response.APIResponse
import com.dicoding.proyekakhir.core.model.response.movies.MoviesDetailsResponse
import com.dicoding.proyekakhir.core.model.response.movies.MoviesResponse
import com.dicoding.proyekakhir.core.model.response.tv_shows.TvShowsDetailsResponse
import com.dicoding.proyekakhir.core.model.response.tv_shows.TvShowsResponse
import com.dicoding.proyekakhir.core.utils.EspressoIdlingResource.decrement
import com.dicoding.proyekakhir.core.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.dicoding.proyekakhir.core.network.TMDBClient.Companion.getService as TMDBClientGetService

class RemoteDataSource {

    fun getMoviesList(): LiveData<APIResponse<List<MoviesRemote>>> {
        increment()

        MutableLiveData<APIResponse<List<MoviesRemote>>>().also {
            TMDBClientGetService().getMoviesList(PAGE).enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    it.value = APIResponse.success(response.body()?.result as List<MoviesRemote>)

                    decrement()
                }

                override fun onFailure(call: Call<MoviesResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable.message}")

                    decrement()
                }
            })

            return it
        }
    }

    fun getTvShowsList(): LiveData<APIResponse<List<TvShowsRemote>>> {
        increment()

        MutableLiveData<APIResponse<List<TvShowsRemote>>>().also {
            TMDBClientGetService().getTvShowsList(PAGE).enqueue(object : Callback<TvShowsResponse> {
                override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                    it.value = APIResponse.success(response.body()?.result as List<TvShowsRemote>)

                    decrement()
                }

                override fun onFailure(call: Call<TvShowsResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable.message}")

                    decrement()
                }
            })

            return it
        }
    }

    fun getMoviesDetails(moviesID: String): LiveData<APIResponse<MoviesDetailsResponse>> {
        increment()

        MutableLiveData<APIResponse<MoviesDetailsResponse>>().also {
            TMDBClientGetService().getMoviesDetails(moviesID).enqueue(object : Callback<MoviesDetailsResponse> {
                override fun onResponse(call: Call<MoviesDetailsResponse>, response: Response<MoviesDetailsResponse>) {
                    it.value = APIResponse.success(response.body() as MoviesDetailsResponse)

                    decrement()
                }

                override fun onFailure(call: Call<MoviesDetailsResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable.message}")

                    decrement()
                }
            })

            return it
        }
    }

    fun getTvShowsDetails(tvShowsID: String): LiveData<APIResponse<TvShowsDetailsResponse>> {
        increment()

        MutableLiveData<APIResponse<TvShowsDetailsResponse>>().also {
            TMDBClientGetService().getTvShowsDetails(tvShowsID).enqueue(object : Callback<TvShowsDetailsResponse> {
                override fun onResponse(call: Call<TvShowsDetailsResponse>, response: Response<TvShowsDetailsResponse>) {
                    it.value = APIResponse.success(response.body() as TvShowsDetailsResponse)

                    decrement()
                }

                override fun onFailure(call: Call<TvShowsDetailsResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable.message}")

                    decrement()
                }
            })

            return it
        }
    }

    companion object {
        const val TAG = "RemoteDataSource"

        @Volatile
        private var remoteDataSource: RemoteDataSource? = null

        fun getRemoteDataSource(): RemoteDataSource {
            return remoteDataSource ?: synchronized(this) {
                RemoteDataSource().apply {
                    remoteDataSource = this
                }
            }
        }
    }

}