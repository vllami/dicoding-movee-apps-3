package com.dicoding.proyekakhir.data.remote.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.proyekakhir.data.remote.response.APIResponse
import com.dicoding.proyekakhir.data.remote.response.movies.MoviesDetailsResponse
import com.dicoding.proyekakhir.data.remote.response.movies.MoviesRemote
import com.dicoding.proyekakhir.data.remote.response.movies.MoviesResponse
import com.dicoding.proyekakhir.data.remote.response.tv_shows.TvShowsDetailsResponse
import com.dicoding.proyekakhir.data.remote.response.tv_shows.TvShowsRemote
import com.dicoding.proyekakhir.data.remote.response.tv_shows.TvShowsResponse
import com.dicoding.proyekakhir.utils.EspressoIdlingResource.decrement
import com.dicoding.proyekakhir.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.dicoding.proyekakhir.retrofit.TMDBClient.Companion.getService as TMDBClientGetService

class RemoteDataSource {

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

    fun getMoviesList(): LiveData<APIResponse<List<MoviesRemote>>> {
        val result = MutableLiveData<APIResponse<List<MoviesRemote>>>()

        increment()

        TMDBClientGetService().getMoviesList(1).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                result.value = APIResponse.success(response.body()?.result as List<MoviesRemote>)

                decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable.message}")

                decrement()
            }
        })

        return result
    }

    fun getMoviesDetails(id: String): LiveData<APIResponse<MoviesDetailsResponse>> {
        val result = MutableLiveData<APIResponse<MoviesDetailsResponse>>()

        increment()

        TMDBClientGetService().getMoviesDetails(id).enqueue(object : Callback<MoviesDetailsResponse> {
            override fun onResponse(call: Call<MoviesDetailsResponse>, response: Response<MoviesDetailsResponse>) {
                result.value = APIResponse.success(response.body() as MoviesDetailsResponse)

                decrement()
            }

            override fun onFailure(call: Call<MoviesDetailsResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable.message}")

                decrement()
            }
        })

        return result
    }

    fun getTvShowsList(): LiveData<APIResponse<List<TvShowsRemote>>> {
        val result = MutableLiveData<APIResponse<List<TvShowsRemote>>>()

        increment()

        TMDBClientGetService().getTvShowsList(1).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                result.value = APIResponse.success(response.body()?.result as List<TvShowsRemote>)

                decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable.message}")

                decrement()
            }
        })

        return result
    }

    fun getTvShowsDetails(id: String): LiveData<APIResponse<TvShowsDetailsResponse>> {
        val result = MutableLiveData<APIResponse<TvShowsDetailsResponse>>()

        increment()

        TMDBClientGetService().getTvShowsDetails(id).enqueue(object : Callback<TvShowsDetailsResponse> {
            override fun onResponse(call: Call<TvShowsDetailsResponse>, response: Response<TvShowsDetailsResponse>) {
                result.value = APIResponse.success(response.body() as TvShowsDetailsResponse)

                decrement()
            }

            override fun onFailure(call: Call<TvShowsDetailsResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable.message}")

                decrement()
            }
        })

        return result
    }

}