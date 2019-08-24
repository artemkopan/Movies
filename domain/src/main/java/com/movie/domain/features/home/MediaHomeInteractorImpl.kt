package com.movie.domain.features.home

import com.movie.domain.entities.Media

class MediaHomeInteractorImpl(
    private val repo: MediaHomeRepository
) : MediaHomeInteractor {

    override suspend fun getMediaList(): List<Media> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}