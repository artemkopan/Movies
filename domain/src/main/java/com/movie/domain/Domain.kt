package com.movie.domain

import com.movie.domain.features.genres.GetGenresUseCase
import com.movie.domain.features.home.GetMediaListUseCase
import com.movie.domain.system.MemoryStore
import com.movie.domain.system.Store
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

val domain = module {

    single<Store>(MemoryStoreQualifier) { MemoryStore() }

    //region Use Cases
    factory { GetGenresUseCase(get(), get(MemoryStoreQualifier)) }
    factory { GetMediaListUseCase(get(), get()) }

    //endregion

    //region Interactors

    //endregion

}


object MemoryStoreQualifier : Qualifier