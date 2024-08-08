package com.ndc.moviekuh.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ndc.moviekuh.data.source.local.room.dao.FavoriteMovieDao
import com.ndc.moviekuh.data.source.local.room.dto.FavoriteDto
import com.ndc.moviekuh.data.source.network.pagging.NowPlayingMoviePagingSource
import com.ndc.moviekuh.data.source.network.pagging.PopularMoviePagingSource
import com.ndc.moviekuh.data.source.network.pagging.TopRatedMoviePagingSource
import com.ndc.moviekuh.data.source.network.response.NowPlayingMovieItem
import com.ndc.moviekuh.data.source.network.response.PopularMovieItem
import com.ndc.moviekuh.data.source.network.response.TopRatedMovieItem
import com.ndc.moviekuh.data.source.network.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val favoriteMovieDao: FavoriteMovieDao
) {
    fun getAllFavoriteList()
    = favoriteMovieDao.getAllFavoriteList()

    suspend fun addToFavorite(favoriteDto: FavoriteDto)
    = favoriteMovieDao.addToFavorite(favoriteDto)

    suspend fun deleteMovieById(movieId: Int)
    = favoriteMovieDao.deleteById(movieId)

    suspend fun isFavorite(movieId: Int)
    = favoriteMovieDao.isFavorite(movieId)

    fun getPopularMoviePaging(): Flow<PagingData<PopularMovieItem>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = {
            PopularMoviePagingSource(movieService)
        }
    ).flow

    fun getNowPlayingMoviePaging(): Flow<PagingData<NowPlayingMovieItem>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = {
            NowPlayingMoviePagingSource(movieService)
        }
    ).flow

    fun getTopRatedMoviePaging() : Flow<PagingData<TopRatedMovieItem>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = {
            TopRatedMoviePagingSource(movieService)
        }
    ).flow

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