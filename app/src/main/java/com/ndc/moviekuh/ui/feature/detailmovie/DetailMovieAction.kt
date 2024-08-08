package com.ndc.moviekuh.ui.feature.detailmovie

import com.ndc.moviekuh.data.source.local.room.dto.FavoriteDto

sealed interface DetailMovieAction {
    data class OnFavoriteChange(
        val favoriteDto: FavoriteDto
    ) : DetailMovieAction
    data class OnGetIsFavorite(
        val id:Int
    ) : DetailMovieAction
}