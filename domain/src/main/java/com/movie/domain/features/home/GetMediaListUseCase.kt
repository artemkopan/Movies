package com.movie.domain.features.home

import com.movie.domain.common.Logger
import com.movie.domain.common.UseCaseAsync
import com.movie.domain.entities.Media
import com.movie.domain.features.genres.GetGenresUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetMediaListUseCase(
    private val genresUseCase: GetGenresUseCase,
    private val repo: MediaHomeRepository
) : UseCaseAsync<List<Media>> {

    override suspend fun execute(): List<Media> = withContext(Dispatchers.IO) {
        val genresDef = async { Logger.d("load genres"); genresUseCase.execute(tryLoadCache = true) }
        val mediaDef = async { Logger.d("load media"); repo.getMediaList() }

        val genres = genresDef.await().associateBy { it.id }.also { Logger.d("Loaded genres") }

        mediaDef.await().also { Logger.d("Loaded media") }.map { raw ->
            Media(
                raw,
                raw.genres.mapNotNull { genres[it] }
            )
        }

    }
}