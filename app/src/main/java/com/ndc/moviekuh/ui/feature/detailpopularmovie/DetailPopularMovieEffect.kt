package com.ndc.moviekuh.ui.feature.detailpopularmovie

sealed interface DetailPopularMovieEffect {
    data object Empty: DetailPopularMovieEffect
    data class OnShowToast(
        val message: String
    ) : DetailPopularMovieEffect
}