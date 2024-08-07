package com.ndc.moviekuh.data.source.network.service

import com.ndc.moviekuh.data.source.network.response.NowPlayingMovieResponse
import com.ndc.moviekuh.data.source.network.response.PopularMovieResponse
import com.ndc.moviekuh.data.source.network.response.TopRatedMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): PopularMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): NowPlayingMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRatedMovieResponse
}