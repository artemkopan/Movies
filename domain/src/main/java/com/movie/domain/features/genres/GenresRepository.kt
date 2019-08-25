package com.movie.domain.features.genres

import com.movie.domain.entities.Genre

interface GenresRepository {

    suspend fun getGenres() : List<Genre>

}