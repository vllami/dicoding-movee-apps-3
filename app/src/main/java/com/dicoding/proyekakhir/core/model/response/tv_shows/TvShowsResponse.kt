package com.dicoding.proyekakhir.core.model.response.tv_shows

import com.dicoding.proyekakhir.core.model.remote.tv_shows.TvShowsRemote
import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("results")
    val result: List<TvShowsRemote>
)