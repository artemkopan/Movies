package com.movie.data.network.response

import com.google.gson.annotations.SerializedName

open class ResultResponse<T>(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results", alternate = ["genres"])
    val results: T? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)