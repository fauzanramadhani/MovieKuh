package com.ndc.moviekuh.domain

import com.ndc.moviekuh.data.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviePagingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke() = movieRepository.getPopularMoviePaging()
}