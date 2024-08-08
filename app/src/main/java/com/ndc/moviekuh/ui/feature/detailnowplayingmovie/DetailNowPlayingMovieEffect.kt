package com.ndc.moviekuh.ui.feature.detailnowplayingmovie

sealed interface DetailNowPlayingMovieEffect {
    data object Empty: DetailNowPlayingMovieEffect
    data class OnShowToast(
        val message: String
    ) : DetailNowPlayingMovieEffect
}