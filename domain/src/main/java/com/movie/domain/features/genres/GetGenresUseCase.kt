package com.movie.domain.features.genres

import com.movie.domain.common.UseCaseAsyncParams
import com.movie.domain.entities.Genre
import com.movie.domain.system.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetGenresUseCase(
    private val repository: GenresRepository,
    private val store: Store
) : UseCaseAsyncParams<Boolean, List<Genre>> {

    companion object {
        private const val GENRES = "genres"
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun execute(tryLoadCache: Boolean): List<Genre> = withContext(Dispatchers.IO) {
        store.get<List<Genre>>(GENRES) ?: repository.getGenres().also {
            store.put(GENRES, it)
        }
    }
}