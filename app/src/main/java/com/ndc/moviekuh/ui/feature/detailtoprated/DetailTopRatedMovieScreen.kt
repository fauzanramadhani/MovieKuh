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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ndc.moviekuh.data.source.network.response.TopRatedMovieItem
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
    topRatedMoviePagingItems: LazyPagingItems<TopRatedMovieItem>,
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBarSecondary(
                title = state.titleAppBar
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
            contentPadding = PaddingValues(16.dp),
            state = lazyListState
        ) {
            items(
                key = { it },
                count = topRatedMoviePagingItems.itemCount
            ) {
                topRatedMoviePagingItems[it]?.let { movie ->
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
                                summary = movie.overview,
                                id = movie.id
                            )
                        ) {
                            launchSingleTop = true
                        }
                    }
                }
            }
            topRatedMoviePagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
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
                    }

                    loadState.refresh is LoadState.Error -> retry()

                    loadState.append is LoadState.Error -> retry()

                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = color.primary)
                            }
                        }
                    }
                }
            }
        }
    }
}