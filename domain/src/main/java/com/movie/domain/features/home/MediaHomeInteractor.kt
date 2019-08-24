package com.movie.domain.features.home

import com.movie.domain.entities.Media

interface MediaHomeInteractor {

    suspend fun getMediaList(): List<Media>

}