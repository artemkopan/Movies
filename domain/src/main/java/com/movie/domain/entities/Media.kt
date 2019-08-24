package com.movie.domain.entities

data class Media(
    val id: String,
    val preview: Image,
    val title: String,
    val overview: String,
    val genre: String,
    val releaseDate: Date,
    val genres: List<Genre>
)