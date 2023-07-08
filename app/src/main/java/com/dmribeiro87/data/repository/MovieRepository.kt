package com.dmribeiro87.data.repository


import com.dmribeiro87.data.model.MovieResponse
import com.dmribeiro87.data.remote.RetrofitClient
import retrofit2.Response

class MovieRepository{

    suspend fun getNowPlaying(pageNumber: Int) : Response<MovieResponse> {
        return RetrofitClient.createService.getNowPlaying(page = pageNumber)
    }

}