package com.dicoding.proyekakhir.utils

import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.data.remote.response.movies.MoviesDetailsResponse
import com.dicoding.proyekakhir.data.remote.response.tv_shows.TvShowsDetailsResponse

object DetailsDummyData {

    fun getMoviesDetails(): MoviesEntity {
        return MoviesEntity(
            567189,
            "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "Tom Clancy's Without Remorse",
            7.3,
            "2021-04-29",
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife."
        )
    }

    fun getTvShowsDetails(): TvShowsEntity {
        return TvShowsEntity(
            88396,
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "The Falcon and the Winter Soldier",
            7.9,
            "2021-03-19",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
        )
    }

    fun getMoviesDetailsRemote(): MoviesDetailsResponse {
        return MoviesDetailsResponse(
            id = 567189,
            backdrop = "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            poster = "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            title = "Tom Clancy's Without Remorse",
            rating = 7.3,
            releaseDate = "2021-04-29",
            synopsis = "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
        )
    }

    fun getTvShowsDetailsRemote(): TvShowsDetailsResponse {
        return TvShowsDetailsResponse(
            id = 88396,
            backdrop = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            poster = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            title = "The Falcon and the Winter Soldier",
            rating = 7.9,
            releaseDate = "2021-03-19",
            synopsis = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
        )
    }

}