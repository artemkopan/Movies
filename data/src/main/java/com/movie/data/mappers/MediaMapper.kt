package com.movie.data.mappers

import com.movie.data.network.response.MediaResponse
import com.movie.domain.common.Mapper
import com.movie.domain.entities.Genre
import com.movie.domain.entities.Image
import com.movie.domain.entities.Media
import com.movie.domain.system.SystemConfig

class MediaMapper(private val systemConfig: SystemConfig) : Mapper<Media, MediaResponse>() {

    override fun map(from: MediaResponse, params: Any?): Media = with(from) {
        @Suppress("UNCHECKED_CAST")
        val genres = params as Map<Int, Genre>
        Media(
            id.toString(),
            Image(systemConfig.posterUrl + posterPath.orEmpty()),
            title.orEmpty(),
            overview.orEmpty(),
            releaseDate.orEmpty(),
            from.genreIds.orEmpty().mapNotNull { genres[it] }
        )
    }
}