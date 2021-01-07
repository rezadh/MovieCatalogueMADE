package com.rezadev.core.data.source.remote.network


import com.rezadev.core.data.source.remote.response.ListMovieResponse
import com.rezadev.core.data.source.remote.response.ListMovieTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String? = "13e37e4addbd7c9ab9faff42ec24895a"
    ): ListMovieResponse

    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("api_key") apiKey: String? = "13e37e4addbd7c9ab9faff42ec24895a"
    ): ListMovieTvResponse
}