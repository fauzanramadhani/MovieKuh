package com.ndc.moviekuh.ui.feature.detailmovie

sealed interface DetailMovieEffect {
    data object Empty : DetailMovieEffect
}