package com.movie.data

import com.movie.data.mappers.MediaRawMapper
import org.koin.dsl.module

val mappers = module {
    factory { MediaRawMapper() }
}