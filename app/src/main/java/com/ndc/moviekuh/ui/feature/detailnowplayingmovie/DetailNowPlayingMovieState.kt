package com.ndc.moviekuh.ui.feature.detailnowplayingmovie

import com.ndc.moviekuh.data.source.network.response.NowPlayingMovieItem

data class DetailNowPlayingMovieState(
    val nowPlayingMovieList: List<NowPlayingMovieItem> = emptyList(),
    val nowPlayingMovieLoading: Boolean = true,
)
