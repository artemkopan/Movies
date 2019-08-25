package com.movie.data.mappers

import com.movie.data.network.response.GenreResponse
import com.movie.domain.common.Mapper
import com.movie.domain.entities.Genre

class GenreMapper : Mapper<Genre, GenreResponse>() {
    override fun map(from: GenreResponse, params: Any?): Genre = with(from) {
        Genre(
            id ?: -1,
            name.orEmpty()
        )
    }
}