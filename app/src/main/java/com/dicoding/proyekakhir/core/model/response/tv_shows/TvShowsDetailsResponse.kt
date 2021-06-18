package com.dicoding.proyekakhir.core.model.response.tv_shows

import com.google.gson.annotations.SerializedName

data class TvShowsDetailsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("first_air_date")
    val releaseDate: String,
    @SerializedName("overview")
    val synopsis: String
)