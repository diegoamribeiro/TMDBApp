package com.dmribeiro87.data

import com.dmribeiro87.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = "f321a808e68611f41312aa8408531476",
        @Query("page") page: Int,
        @Query("language") lang: String = "pt-BR"
    ): Response<MovieResponse>
}