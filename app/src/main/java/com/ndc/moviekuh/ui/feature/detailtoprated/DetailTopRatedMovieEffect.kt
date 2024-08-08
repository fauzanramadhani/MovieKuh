package com.ndc.moviekuh.ui.feature.detailtoprated

sealed interface DetailTopRatedMovieEffect {
    data object Empty: DetailTopRatedMovieEffect
    data class OnShowToast(
        val message: String
    ) : DetailTopRatedMovieEffect
}