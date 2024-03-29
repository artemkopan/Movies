package com.movie.data.network.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)