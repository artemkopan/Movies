package com.movie.domain

import com.movie.domain.features.home.*
import org.koin.dsl.module

val domain = module {

    factory <MediaHomeInteractor>{ MediaHomeInteractorImpl(get()) }

}