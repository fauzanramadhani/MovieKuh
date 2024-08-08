package com.ndc.moviekuh.domain

import com.ndc.moviekuh.data.repository.MovieRepository
import javax.inject.Inject

class DeleteFavoriteByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) = movieRepository.deleteMovieById(movieId)
}