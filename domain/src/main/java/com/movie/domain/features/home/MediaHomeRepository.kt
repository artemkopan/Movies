package com.movie.domain.features.home

import com.movie.domain.entities.Media

interface MediaHomeRepository {

    suspend fun getMediaList(): List<Media>

}