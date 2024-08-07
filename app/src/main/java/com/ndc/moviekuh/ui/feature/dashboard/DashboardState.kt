package com.ndc.moviekuh.ui.feature.dashboard

import com.ndc.moviekuh.data.source.network.response.NowPlayingMovieItem
import com.ndc.moviekuh.data.source.network.response.PopularMovieItem
import com.ndc.moviekuh.data.source.network.response.TopRatedMovieItem

data class DashboardState(
    val currentScreen: Int = 0,
    val bottomSheetVisible: Boolean = false,
    val dashboardBottomSheetType: HomeBottomSheetType = HomeBottomSheetType.NotReady,

    // main screen
    val popularMovieLoading: Boolean = true,
    val nowPlayingMovieLoading: Boolean = true,
    val topRatedMovieLoading: Boolean = true,
    val popularMovieList: List<PopularMovieItem> = emptyList(),
    val nowPlayingMovieList: List<NowPlayingMovieItem> = emptyList(),
    val topRatedMovieList: List<TopRatedMovieItem> = emptyList(),
)
