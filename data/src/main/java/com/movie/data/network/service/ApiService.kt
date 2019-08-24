package com.movie.data.network.service

import com.movie.data.network.response.MediaResponse
import com.movie.data.network.response.ResultResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ResultResponse<List<MediaResponse>>>

}