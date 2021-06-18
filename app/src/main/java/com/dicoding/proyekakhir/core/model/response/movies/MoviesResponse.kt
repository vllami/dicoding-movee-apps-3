package com.dicoding.proyekakhir.core.model.response.movies

import com.dicoding.proyekakhir.core.model.remote.movies.MoviesRemote
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val result: List<MoviesRemote>
)