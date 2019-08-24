package com.movie.domain.entities

data class Media(
    val raw: MediaRaw,
    val genres: List<Genre>
)