package com.ndc.moviekuh.ui.feature.dashboard.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ndc.moviekuh.R
import com.ndc.moviekuh.ui.component.button.PrimaryIconButton
import com.ndc.moviekuh.ui.component.card.PrimaryMovieCard
import com.ndc.moviekuh.ui.component.shimmer.shimmerBrush
import com.ndc.moviekuh.ui.feature.dashboard.DashboardAction
import com.ndc.moviekuh.ui.feature.dashboard.DashboardState
import com.ndc.moviekuh.ui.navigation.NavRoute

@Composable
fun MainScreen(
    navHostController: NavHostController,
    verticalScrollState: ScrollState,
    popularListState: LazyListState,
    nowPlayingListState: LazyListState,
    topRatedListState: LazyListState,
    paddingValues: PaddingValues,
    state: DashboardState,
    action: (DashboardAction) -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    bottom = paddingValues.calculateBottomPadding() + 16.dp
                )
            )
            .verticalScroll(verticalScrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Film Populer",
                style = typography.titleSmall,
                color = color.onBackground
            )
            PrimaryIconButton(
                text = "Lihat Semua",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                        contentDescription = "",
                        tint = color.onPrimary,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            )
        }
        LazyRow(
            state = popularListState,
            contentPadding = PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                end = paddingValues.calculateLeftPadding(layoutDirection) + 16.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.popularMovieLoading)
                items(2) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(shimmerBrush())
                            .width(158.dp)
                            .height(246.dp)
                    )
                }
            else
                items(
                    key = { it.id },
                    items = state.popularMovieList
                ) { movie ->
                    PrimaryMovieCard(
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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Sedang Tayang",
                style = typography.titleSmall,
                color = color.onBackground
            )
            PrimaryIconButton(
                text = "Lihat Semua",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                        contentDescription = "",
                        tint = color.onPrimary,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            )
        }
        LazyRow(
            state = nowPlayingListState,
            contentPadding = PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                end = paddingValues.calculateLeftPadding(layoutDirection) + 16.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.nowPlayingMovieLoading)
                items(2) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(shimmerBrush())
                            .width(158.dp)
                            .height(246.dp)
                    )
                }
            else
                items(
                    key = { it.id },
                    items = state.nowPlayingMovieList
                ) { movie ->
                    PrimaryMovieCard(
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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Peringkat Teratas",
                style = typography.titleSmall,
                color = color.onBackground
            )
            PrimaryIconButton(
                text = "Lihat Semua",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                        contentDescription = "",
                        tint = color.onPrimary,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            )
        }
        LazyRow(
            state = topRatedListState,
            contentPadding = PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                end = paddingValues.calculateLeftPadding(layoutDirection) + 16.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.topRatedMovieLoading)
                items(2) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(shimmerBrush())
                            .width(158.dp)
                            .height(246.dp)
                    )
                }
            else
                items(
                    key = { it.id },
                    items = state.topRatedMovieList
                ) { movie ->
                    PrimaryMovieCard(
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