package com.ndc.moviekuh.ui.feature.dashboard.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ndc.moviekuh.ui.component.card.SecondaryMovieCard
import com.ndc.moviekuh.ui.component.topbar.TopBarSecondary
import com.ndc.moviekuh.ui.feature.dashboard.DashboardAction
import com.ndc.moviekuh.ui.feature.dashboard.DashboardState
import com.ndc.moviekuh.ui.navigation.NavRoute

@Composable
fun FavoriteScreen(
    navHostController: NavHostController,
    listState: LazyListState,
    paddingValues: PaddingValues,
    state: DashboardState,
    action: (DashboardAction) -> Unit
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        state = listState
    ) {
        items(state.favoriteMovieList) { movie ->
            SecondaryMovieCard(
                movieImage = "https://image.tmdb.org/t/p/original${movie.posterPath}",
                movieName = movie.title,
                movieRating = movie.voteAverage
            ) {
                navHostController.navigate(
                    NavRoute.DetailMovieScreen.navigateWithData(
                        imageUrl = "https://image.tmdb.org/t/p/original${movie.posterPath}",
                        title = movie.title,
                        genreList = movie.genreIds,
                        rating = movie.voteAverage,
                        ratingCount = movie.voteCount,
                        release = movie.releaseDate,
                        isAdult = movie.adult,
                        summary = movie.overview,
                        id = movie.id
                    )
                ) {
                    launchSingleTop = true
                }
            }
        }
    }
}