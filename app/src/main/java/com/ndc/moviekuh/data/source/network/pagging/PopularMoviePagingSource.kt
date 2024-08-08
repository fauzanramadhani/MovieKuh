package com.ndc.moviekuh.data.source.network.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ndc.moviekuh.data.source.network.response.PopularMovieItem
import com.ndc.moviekuh.data.source.network.service.MovieService
import retrofit2.HttpException
import java.io.IOException

class PopularMoviePagingSource(
    private val movieService: MovieService,
) : PagingSource<Int, PopularMovieItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieItem> {
        return try {
            val currentPage = params.key ?: 1
            val movies = movieService.getPopularMovies(
                language = "en-US",
                page = currentPage,
            )
            LoadResult.Page(
                data = movies.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.results.isEmpty() || movies.page == movies.totalPages) null else movies.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMovieItem>): Int? {
        return state.anchorPosition
    }
}