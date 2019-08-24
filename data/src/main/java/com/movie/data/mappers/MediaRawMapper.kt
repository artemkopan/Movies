package com.movie.data.mappers

import com.movie.data.network.response.MediaResponse
import com.movie.domain.common.Mapper
import com.movie.domain.entities.Image
import com.movie.domain.entities.MediaRaw

class MediaRawMapper : Mapper<MediaRaw, MediaResponse>() {
    override fun map(from: MediaResponse, params: Any?): MediaRaw = with(from) {
        MediaRaw(
            id.toString(),
            Image(posterPath.orEmpty()),
            title.orEmpty(),
            overview.orEmpty(),
            releaseDate.orEmpty(),
            genreIds.orEmpty()
        )
    }
}