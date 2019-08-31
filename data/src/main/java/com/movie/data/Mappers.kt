package com.movie.data

import com.movie.data.mappers.GenreMapper
import com.movie.data.mappers.MediaMapper
import org.koin.dsl.module

val mappers = module {
    factory { MediaMapper(get()) }
    factory { GenreMapper() }
}