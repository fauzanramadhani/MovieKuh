package com.ndc.moviekuh.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.ndc.moviekuh.ui.feature.dashboard.DashboardEffect
import com.ndc.moviekuh.ui.feature.dashboard.DashboardScreen
import com.ndc.moviekuh.ui.feature.dashboard.DashboardViewModel
import com.ndc.moviekuh.ui.feature.detailmovie.DetailMovieEffect
import com.ndc.moviekuh.ui.feature.detailmovie.DetailMovieViewModel
import com.ndc.moviekuh.ui.feature.detailmovie.DetailMovieScreen
import com.ndc.moviekuh.ui.feature.detailnowplayingmovie.DetailNowPlayingMovieEffect
import com.ndc.moviekuh.ui.feature.detailnowplayingmovie.DetailNowPlayingMovieScreen
import com.ndc.moviekuh.ui.feature.detailnowplayingmovie.DetailNowPlayingMovieViewModel
import com.ndc.moviekuh.ui.feature.detailpopularmovie.DetailPopularMovieEffect
import com.ndc.moviekuh.ui.feature.detailpopularmovie.DetailPopularMovieScreen
import com.ndc.moviekuh.ui.feature.detailpopularmovie.DetailPopularMovieViewModel
import com.ndc.moviekuh.ui.feature.detailtoprated.DetailTopRatedMovieEffect
import com.ndc.moviekuh.ui.feature.detailtoprated.DetailTopRatedMovieScreen
import com.ndc.moviekuh.ui.feature.detailtoprated.DetailTopRatedMovieViewModel
import com.ndc.moviekuh.ui.feature.search.SearchEffect
import com.ndc.moviekuh.ui.feature.search.SearchScreen
import com.ndc.moviekuh.ui.feature.search.SearchViewModel
import com.ndc.moviekuh.utils.decode

@Composable
fun SetupNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoute.DashboardScreen.route,
        route = NavRoute.MainRoute.route,
    ) {
        composable(
            route = NavRoute.DashboardScreen.route
        ) {
            val viewModel = hiltViewModel<DashboardViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.onEffect.collectAsStateWithLifecycle(initialValue = DashboardEffect.Empty)

            DashboardScreen(
                navHostController = navHostController,
                state = state,
                effect = effect,
                action = viewModel::onAction
            )
        }

        composable(
            route = NavRoute.DetailMovieScreen.route,
            arguments = listOf(
                navArgument(keyA) {
                    type = NavType.StringType
                },
                navArgument(keyB) {
                    type = NavType.StringType
                },
                navArgument(keyC) {
                    type = NavType.StringType
                },
                navArgument(keyD) {
                    type = NavType.FloatType
                },
                navArgument(keyE) {
                    type = NavType.IntType
                },
                navArgument(keyF) {
                    type = NavType.StringType
                },
                navArgument(keyG) {
                    type = NavType.BoolType
                },
                navArgument(keyH) {
                    type = NavType.StringType
                },
                navArgument(keyI) {
                    type = NavType.IntType
                },
            )
        ) { navBackStackEntry ->
            val viewModel = hiltViewModel<DetailMovieViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.onEffect.collectAsStateWithLifecycle(initialValue = DetailMovieEffect.Empty)
            val imageUrl = navBackStackEntry.arguments?.getString(keyA)?.decode() ?: ""
            val title = navBackStackEntry.arguments?.getString(keyB)?.decode() ?: ""
            val genreList = (navBackStackEntry.arguments?.getString(keyC)?.decode() ?: "")
                .split(", ").map { it.toInt() }
            val rating = navBackStackEntry.arguments?.getFloat(keyD) ?: 0f
            val ratingCount = navBackStackEntry.arguments?.getInt(keyE) ?: 0
            val release = navBackStackEntry.arguments?.getString(keyF)?.decode() ?: ""
            val isAdult = navBackStackEntry.arguments?.getBoolean(keyG) ?: false
            val summary = navBackStackEntry.arguments?.getString(keyH)?.decode() ?: ""
            val id = navBackStackEntry.arguments?.getInt(keyI) ?: 0

            DetailMovieScreen(
                navHostController = navHostController,
                state = state,
                effect = effect,
                action = viewModel::onAction,
                imageUrl = imageUrl,
                title = title,
                genreList = genreList,
                rating = rating,
                ratingCount = ratingCount,
                release = release,
                isAdult = isAdult,
                summary = summary,
                id = id
            )
        }

        composable(
            route = NavRoute.DetailPopularMovieScreen.route
        ) {
            val viewModel = hiltViewModel<DetailPopularMovieViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.onEffect.collectAsStateWithLifecycle(initialValue = DetailPopularMovieEffect.Empty)
            val popularMoviePagingItems = viewModel.popularMoviePagingState.collectAsLazyPagingItems()

            DetailPopularMovieScreen(
                navHostController = navHostController,
                state = state,
                effect = effect,
                action = viewModel::onAction,
                popularMoviePagingItems = popularMoviePagingItems
            )
        }

        composable(
            route = NavRoute.DetailNowPlayingMovieScreen.route
        ) {
            val viewModel = hiltViewModel<DetailNowPlayingMovieViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.onEffect.collectAsStateWithLifecycle(initialValue = DetailNowPlayingMovieEffect.Empty)
            val nowPlayingMoviePagingItems = viewModel.nowPlayingMoviePagingState.collectAsLazyPagingItems()

            DetailNowPlayingMovieScreen(
                navHostController = navHostController,
                state = state,
                effect = effect,
                action = viewModel::onAction,
                nowPlayingMoviePagingItems = nowPlayingMoviePagingItems
            )
        }

        composable(
            route = NavRoute.DetailTopRatedMovieScreen.route
        ) {
            val viewModel = hiltViewModel<DetailTopRatedMovieViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.onEffect.collectAsStateWithLifecycle(initialValue = DetailTopRatedMovieEffect.Empty)
            val topRatedMoviePagingItems = viewModel.topRatedMoviePagingState.collectAsLazyPagingItems()

            DetailTopRatedMovieScreen(
                navHostController = navHostController,
                state = state,
                effect = effect,
                action = viewModel::onAction,
                topRatedMoviePagingItems = topRatedMoviePagingItems
            )
        }

        composable(
            route = NavRoute.SearchScreen.route
        ) {
            val viewModel = hiltViewModel<SearchViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.onEffect.collectAsStateWithLifecycle(initialValue = SearchEffect.Empty)

            SearchScreen(
                navHostController = navHostController,
                state = state,
                effect = effect,
                action = viewModel::onAction
            )
        }
    }
}