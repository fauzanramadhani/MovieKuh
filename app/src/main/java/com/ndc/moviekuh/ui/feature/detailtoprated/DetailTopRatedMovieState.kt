package com.ndc.moviekuh.ui.feature.detailtoprated

import com.ndc.moviekuh.data.source.network.response.TopRatedMovieItem

data class DetailTopRatedMovieState(
    val topRatedMovieList: List<TopRatedMovieItem> = emptyList(),
    val topRatedMovieLoading: Boolean = true,
)
