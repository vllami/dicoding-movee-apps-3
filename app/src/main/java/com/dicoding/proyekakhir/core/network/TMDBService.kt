package com.dicoding.proyekakhir.core.network

import com.dicoding.proyekakhir.BuildConfig.TMDB_API_KEY
import com.dicoding.proyekakhir.core.model.response.movies.MoviesDetailsResponse
import com.dicoding.proyekakhir.core.model.response.movies.MoviesResponse
import com.dicoding.proyekakhir.core.model.response.tv_shows.TvShowsDetailsResponse
import com.dicoding.proyekakhir.core.model.response.tv_shows.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular?api_key=$TMDB_API_KEY")
    fun getMoviesList(@Query("page") page: Int): Call<MoviesResponse>

    @GET("movie/{movie_id}?api_key=$TMDB_API_KEY")
    fun getMoviesDetails(@Path("movie_id") id: String): Call<MoviesDetailsResponse>

    @GET("tv/popular?api_key=$TMDB_API_KEY")
    fun getTvShowsList(@Query("page") page: Int): Call<TvShowsResponse>

    @GET("tv/{tv_id}?api_key=$TMDB_API_KEY")
    fun getTvShowsDetails(@Path("tv_id") id: String): Call<TvShowsDetailsResponse>

}