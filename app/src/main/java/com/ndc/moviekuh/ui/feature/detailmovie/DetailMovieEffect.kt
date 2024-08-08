package com.ndc.moviekuh.ui.feature.detailmovie

sealed interface DetailMovieEffect {
    data object Empty : DetailMovieEffect
    data class OnShowToast(
        val message: String
    ) : DetailMovieEffect
}