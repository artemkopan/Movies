package com.movie.data.features.home

import com.movie.data.mappers.MediaMapper
import com.movie.data.network.service.ApiService
import com.movie.domain.common.Logger
import com.movie.domain.entities.Media
import com.movie.domain.features.genres.GetGenresUseCase
import com.movie.domain.features.home.MediaHomeRepository
import com.movie.domain.system.KeysProvider
import com.movie.domain.system.SystemConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.await

class MediaHomeRepositoryImpl(
    private val genresUseCase: GetGenresUseCase,
    private val keysProvider: KeysProvider,
    private val systemConfig: SystemConfig,
    private val apiService: ApiService,
    private val mediaMapper: MediaMapper
) : MediaHomeRepository {

    override suspend fun getMediaList(): List<Media> = withContext(Dispatchers.IO) {
        val genresDef = async {
            Logger.d("load genres")
            genresUseCase.execute(tryLoadCache = true)
        }
        val mediaListDef = async {
            Logger.d("load media")
            apiService.getUpcoming(keysProvider.apiKey, systemConfig.deviceLanguage, 1)
                .await()
                .results
                .orEmpty()//todo add pagination
        }
        val genres = genresDef.await().associateBy { it.id }.also { Logger.d("Loaded genres") }
        mediaListDef.await()
            .also { Logger.d("Loaded media") }
            .let { mediaMapper.mapList(it, genres) }
    }


}