package com.movie.domain.entities

data class MediaRaw(
    val id: String,
    val preview: Image,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val genres: List<Int>
)