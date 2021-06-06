package com.dicoding.proyekakhir.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_entity")
data class MoviesEntity(
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
    @ColumnInfo(name = "add_watchlist")
    var addWatchlist: Boolean = false
)