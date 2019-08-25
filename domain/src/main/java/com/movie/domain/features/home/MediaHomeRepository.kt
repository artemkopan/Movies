package com.movie.domain.features.home

import com.movie.domain.entities.Genre
import com.movie.domain.entities.MediaRaw

interface MediaHomeRepository {

    suspend fun getMediaList(): List<MediaRaw>

}