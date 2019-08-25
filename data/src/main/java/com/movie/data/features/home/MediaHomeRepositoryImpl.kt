package com.movie.data.features.home

import com.movie.data.mappers.MediaRawMapper
import com.movie.data.network.service.ApiService
import com.movie.domain.entities.Genre
import com.movie.domain.entities.MediaRaw
import com.movie.domain.features.home.MediaHomeRepository
import com.movie.domain.system.KeysProvider
import com.movie.domain.system.SystemConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class MediaHomeRepositoryImpl(
    private val keysProvider: KeysProvider,
    private val systemConfig: SystemConfig,
    private val apiService: ApiService,
    private val mediaRawMapper: MediaRawMapper
) : MediaHomeRepository {

    override suspend fun getMediaList(): List<MediaRaw> = withContext(Dispatchers.IO) {
        apiService.getUpcoming(keysProvider.apiKey, systemConfig.deviceLanguage, 1) //todo add pagination
            .await()
            .results
            .orEmpty()
            .let { mediaRawMapper.mapList(it) }
    }


}