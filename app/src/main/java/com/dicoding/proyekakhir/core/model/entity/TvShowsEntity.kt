package com.dicoding.proyekakhir.core.model.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.proyekakhir.core.Constant.TV_SHOWS_TABLE

@Entity(tableName = TV_SHOWS_TABLE)
data class TvShowsEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "backdrop")
    val backdrop: String,
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
    @ColumnInfo(name = "watchlist")
    var watchlist: Boolean = false
)