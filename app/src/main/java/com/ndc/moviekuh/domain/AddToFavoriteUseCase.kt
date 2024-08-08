package com.ndc.moviekuh.domain

import com.ndc.moviekuh.data.repository.MovieRepository
import com.ndc.moviekuh.data.source.local.room.dto.FavoriteDto
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(favoriteDto: FavoriteDto)
    = movieRepository.addToFavorite(favoriteDto)
}