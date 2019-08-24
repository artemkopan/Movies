package com.movie.domain.entities

inline class Image(val source: Any) {
    inline fun <reified T> source(): T = source as T
}