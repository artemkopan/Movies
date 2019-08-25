package com.movie.data.features.genres

import com.movie.data.mappers.GenreMapper
import com.movie.data.network.service.ApiService
import com.movie.domain.entities.Genre
import com.movie.domain.features.genres.GenresRepository
import com.movie.domain.system.KeysProvider
import com.movie.domain.system.SystemConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await


class GenresRepositoryImpl(
    private val apiService: ApiService,
    private val keysProvider: KeysProvider,
    private val systemConfig: SystemConfig,
    private val genreMapper: GenreMapper
) : GenresRepository {

    override suspend fun getGenres(): List<Genre> = withContext(Dispatchers.IO) {
        apiService.getGenres(keysProvider.apiKey, systemConfig.deviceLanguage)
            .await()
            .results
            .orEmpty()
            .let { genreMapper.mapList(it) }
    }

}