package com.ndc.moviekuh.ui.feature.detailpopularmovie

import com.ndc.moviekuh.data.source.network.response.PopularMovieItem

data class DetailPopularMovieState(
    val popularMovieList: List<PopularMovieItem> = emptyList(),
    val popularMovieLoading: Boolean = true,
)
