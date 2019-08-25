package com.movie.presentation

import com.movie.presentation.features.home.HomeViewModel
import org.koin.dsl.module

val presentation = module {

    factory { HomeViewModel(get()) }

}