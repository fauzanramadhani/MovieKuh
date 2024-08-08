package com.ndc.moviekuh.ui.feature.detailtoprated

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ndc.moviekuh.ui.component.card.SecondaryMovieCard
import com.ndc.moviekuh.ui.component.shimmer.shimmerBrush
import com.ndc.moviekuh.ui.component.topbar.TopBarSecondary
import com.ndc.moviekuh.ui.navigation.NavRoute

@Composable
fun DetailTopRatedMovieScreen(
    navHostController: NavHostController,
    state: DetailTopRatedMovieState,
    effect: DetailTopRatedMovieEffect,
    action: (DetailTopRatedMovieAction) -> Unit,
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Scaffold(
        topBar = {
            TopBarSecondary(
                title = "Peringkat Teratas"
            ) {
                navHostController.navigateUp()
            }
        },
        modifier = Modifier
            .background(color.primary)
            .statusBarsPadding(),
        containerColor = color.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            if (state.topRatedMovieLoading)
                items(3) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(shimmerBrush())
                            .fillMaxWidth()
                            .width(158.dp)
                            .height(91.dp)
                    )
                }
            else
                items(state.topRatedMovieList) { movie ->
                    SecondaryMovieCard(
                        movieImage = "https://image.tmdb.org/t/p/original${movie.posterPath}",
                        movieName = movie.originalTitle,
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
                                summary = movie.overview
                            )
                        ) {
                            launchSingleTop = true
                        }
                    }
                }
        }
    }
}