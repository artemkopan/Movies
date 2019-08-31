package com.movie.data

import com.movie.data.features.genres.GenresRepositoryImpl
import com.movie.data.features.home.MediaHomeRepositoryImpl
import com.movie.data.system.DateFormatterImpl
import com.movie.data.system.KeysProviderImpl
import com.movie.data.system.SystemConfigImpl
import com.movie.domain.features.genres.GenresRepository
import com.movie.domain.features.home.MediaHomeRepository
import com.movie.domain.system.DateFormatter
import com.movie.domain.system.KeysProvider
import com.movie.domain.system.SystemConfig
import org.koin.dsl.module

val data = module {

    single<DateFormatter>(createdAtStart = true) { DateFormatterImpl(get()) }
    single<KeysProvider> { KeysProviderImpl(get()) }
    single<SystemConfig> { SystemConfigImpl(get()) }

    factory<MediaHomeRepository> { MediaHomeRepositoryImpl(get(), get(), get(), get(), get()) }
    factory<GenresRepository> { GenresRepositoryImpl(get(), get(), get(), get()) }

}