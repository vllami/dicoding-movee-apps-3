package com.dicoding.proyekakhir.data.remote.response.movies

import com.google.gson.annotations.SerializedName

data class MoviesDetailsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val synopsis: String
)