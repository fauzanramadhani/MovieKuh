package com.ndc.moviekuh.ui.navigation

import com.ndc.moviekuh.utils.encode


const val keyA = "KEY_A"
const val keyB = "KEY_B"
const val keyC = "KEY_C"
const val keyD = "KEY_D"
const val keyE = "KEY_E"
const val keyF = "KEY_F"
const val keyG = "KEY_G"
const val keyH = "KEY_H"


sealed class NavRoute(val route: String) {
    data object MainRoute : NavRoute("MAIN_ROUTE")
    data object DashboardScreen : NavRoute("DASHBOARD_SCREEN")
    data object DetailMovieScreen :
        NavRoute("DETAIL_MOVIE_SCREEN/{$keyA}/{$keyB}/{$keyC}/{$keyD}/{$keyE}/{$keyF}/{$keyG}/{$keyH}") {
        fun navigateWithData(
            imageUrl: String,
            title: String,
            genreList: List<Int>,
            rating: Float,
            ratingCount: Int,
            release: String,
            isAdult: Boolean,
            summary: String
        ): String {
            val encodedImageUrl = imageUrl.encode()
            val encodedTitle = title.encode()
            val encodedGenreListString = genreList.joinToString { it.toString() }.encode()
            val encodedRelease = release.encode()
            val encodedSummary = summary.encode()

            return "DETAIL_MOVIE_SCREEN/$encodedImageUrl/$encodedTitle/" +
                    "$encodedGenreListString/$rating/$ratingCount/" +
                    "$encodedRelease/$isAdult/$encodedSummary"
        }
    }
    data object DetailPopularMovieScreen : NavRoute("DETAIL_POPULAR_MOVIE_SCREEN")
    data object DetailNowPlayingMovieScreen : NavRoute("DETAIL_NOW_PLAYING_MOVIE_SCREEN")
    data object DetailTopRatedMovieScreen : NavRoute("DETAIL_TOP_RATED_MOVIE_SCREEN")
    data object SearchScreen : NavRoute("SEARCH_SCREEN")
}