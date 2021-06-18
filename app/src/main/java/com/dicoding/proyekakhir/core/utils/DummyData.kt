package com.dicoding.proyekakhir.core.utils

import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.model.remote.movies.MoviesRemote
import com.dicoding.proyekakhir.core.model.remote.tv_shows.TvShowsRemote

object DummyData {

    fun getMovies(): ArrayList<MoviesEntity> {
        ArrayList<MoviesEntity>().also {
            it.apply {
                add(
                    MoviesEntity(
                        337404,
                        "/8ChCpCYxh9YXusmHwcE9YzP0TSG.jpg",
                        "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                        "Cruella",
                        8.6,
                        "2021-05-26",
                        "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
                    )
                )
                add(
                    MoviesEntity(
                        399566,
                        "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                        "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                        "Godzilla vs. Kong",
                        8.0,
                        "2021-03-24",
                        "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
                    )
                )
                add(
                    MoviesEntity(
                        423108,
                        "/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg",
                        "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                        "The Conjuring: The Devil Made Me Do It",
                        8.2,
                        "2021-05-25",
                        "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense."
                    )
                )
            }

            return it
        }
    }

    fun getTvShows(): ArrayList<TvShowsEntity> {
        ArrayList<TvShowsEntity>().also {
            it.apply {
                add(
                    TvShowsEntity(
                        1399,
                        "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                        "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                        "Game of Thrones?",
                        8.4,
                        "2011-04-17",
                        "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
                    )
                )
                add(
                    TvShowsEntity(
                        1402,
                        "/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                        "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                        "The Walking Dead",
                        8.1,
                        "2010-10-31",
                        "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way."
                    )
                )
                add(
                    TvShowsEntity(
                        1416,
                        "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                        "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                        "Grey's Anatomy",
                        8.2,
                        "2005-03-27",
                        "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
                    )
                )
            }

            return it
        }
    }

    fun getMoviesRemote(): ArrayList<MoviesRemote> {
        ArrayList<MoviesRemote>().also {
            it.apply {
                add(
                    MoviesRemote(
                        id = 337404,
                        backdrop = "/8ChCpCYxh9YXusmHwcE9YzP0TSG.jpg",
                        poster = "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                        title = "Cruella",
                        rating = 8.6,
                        releaseDate = "2021-05-26",
                        synopsis = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
                    )
                )
                add(
                    MoviesRemote(
                        id = 399566,
                        backdrop = "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                        poster = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                        title = "Godzilla vs. Kong",
                        rating = 8.0,
                        releaseDate = "2021-03-24",
                        synopsis = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
                    )
                )
                add(
                    MoviesRemote(
                        id = 423108,
                        backdrop = "/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg",
                        poster = "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                        title = "The Conjuring: The Devil Made Me Do It",
                        rating = 8.2,
                        releaseDate = "2021-05-25",
                        synopsis = "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense."
                    )
                )
            }

            return it
        }
    }

    fun getTvShowsRemote(): ArrayList<TvShowsRemote> {
        ArrayList<TvShowsRemote>().also {
            it.apply {
                add(
                    TvShowsRemote(
                        id = 1399,
                        backdrop = "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                        poster = "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                        title = "Game of Thrones?",
                        rating = 8.4,
                        releaseDate = "2011-04-17",
                        synopsis = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
                    )
                )
                add(
                    TvShowsRemote(
                    id = 1402,
                    backdrop = "/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                    poster = "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                    title = "The Walking Dead",
                    rating = 8.1,
                    releaseDate = "2010-10-31",
                    synopsis = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way."
                    )
                )
                add(
                    TvShowsRemote(
                    id = 1416,
                    backdrop = "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                    poster = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                    title = "Grey's Anatomy",
                    rating = 8.2,
                    releaseDate = "2005-03-27",
                    synopsis = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
                    )
                )
            }

            return it
        }
    }

}