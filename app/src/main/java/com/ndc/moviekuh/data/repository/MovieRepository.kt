package com.ndc.moviekuh.data.repository

import com.ndc.moviekuh.data.source.network.response.NowPlayingMovieItem
import com.ndc.moviekuh.data.source.network.response.PopularMovieItem
import com.ndc.moviekuh.data.source.network.response.TopRatedMovieItem
import com.ndc.moviekuh.data.source.network.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getPopularMovieList(
        language: String,
        page: Int,
        limit: Int
    ): Flow<List<PopularMovieItem>> = flow {
        val response = movieService.getPopularMovies(language, page)
        emit(response.results.take(limit))
    }

    suspend fun getNowPlayingMovieList(
        language: String,
        page: Int,
        limit: Int
    ): Flow<List<NowPlayingMovieItem>> = flow {
        val response = movieService.getNowPlayingMovies(language, page)
        emit(response.results.take(limit))
    }

    suspend fun getTopRatedMovieList(
        language: String,
        page: Int,
        limit: Int
    ): Flow<List<TopRatedMovieItem>> = flow {
        val response = movieService.getTopRatedMovies(language, page)
        emit(response.results.take(limit))
    }
}