package com.ndc.moviekuh.domain

import com.ndc.moviekuh.data.repository.MovieRepository
import javax.inject.Inject

class GetPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        language: String = "en-US",
        page: Int = 1,
        limit: Int = 20
    ) = movieRepository.getPopularMovieList(language, page, limit)
}