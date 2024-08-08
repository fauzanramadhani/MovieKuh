package com.ndc.moviekuh.ui.feature.detailmovie

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ndc.moviekuh.R
import com.ndc.moviekuh.data.source.local.constant.genresMap
import com.ndc.moviekuh.data.source.local.room.dto.FavoriteDto
import com.ndc.moviekuh.ui.component.chip.GenreChip
import com.ndc.moviekuh.ui.component.topbar.TopBarSecondary
import com.ndc.moviekuh.ui.theme.MovieKuhTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailMovieScreen(
    navHostController: NavHostController,
    state: DetailMovieState,
    effect: DetailMovieEffect,
    action: (DetailMovieAction) -> Unit,
    imageUrl: String,
    title: String,
    genreList: List<Int>,
    rating: Float,
    ratingCount: Int,
    release: String,
    isAdult: Boolean,
    summary: String,
    id: Int,
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    LaunchedEffect(Unit) {
        action(DetailMovieAction.OnGetIsFavorite(id))
    }

    Scaffold(
        topBar = {
            TopBarSecondary(
                title = "Detail Film"
            ) {
                navHostController.navigateUp()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(12.dp),
                contentColor = color.onPrimaryContainer,
                containerColor = color.primaryContainer,
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    action(
                        DetailMovieAction.OnFavoriteChange(
                            FavoriteDto(
                                id = id,
                                overview = summary,
                                title = title,
                                genreIds = genreList,
                                posterPath = imageUrl,
                                releaseDate = release,
                                voteAverage = rating,
                                voteCount = ratingCount,
                                adult = isAdult,
                                createdAt = System.currentTimeMillis()
                            )
                        )
                    )
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (state.isFavorite) R.drawable.ic_favorite_fill
                        else R.drawable.ic_favorite
                    ),
                    contentDescription = ""
                )
            }
        },
        modifier = Modifier
            .background(color.primary)
            .statusBarsPadding(),
        containerColor = color.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "",
                    error = painterResource(id = R.drawable.error_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 0.5.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = color.outline
                        )
                        .weight(0.5f),
                )
                Column(
                    modifier = Modifier
                        .weight(0.5f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = title,
                        style = typography.titleMedium,
                        color = color.onBackground
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        genreList.map { genresMap[it] ?: "" }.forEach {
                            GenreChip(text = it)
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(36.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_star_fill),
                                contentDescription = "",
                                tint = color.primary,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Text(
                                text = rating.toString(),
                                style = typography.bodySmall,
                                color = color.onBackground
                            )
                        }
                        VerticalDivider(
                            thickness = 0.5.dp,
                            color = color.outline
                        )
                        Text(
                            text = "$ratingCount Ulasan",
                            style = typography.bodySmall,
                            color = color.secondary
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_date_fill),
                            contentDescription = "",
                            tint = color.primary,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Text(
                            text = release,
                            style = typography.bodySmall,
                            color = color.onBackground
                        )
                    }
                    if (isAdult)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_user_secondary_fill),
                                contentDescription = "",
                                tint = color.primary,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Text(
                                text = "17+",
                                style = typography.bodySmall,
                                color = color.onBackground
                            )
                        }
                }
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color.primaryContainer)
                    .padding(12.dp)
            ) {
                Text(
                    text = summary,
                    style = typography.bodySmall,
                    color = color.onPrimaryContainer
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    MovieKuhTheme {
        DetailMovieScreen(
            navHostController = rememberNavController(),
            effect = DetailMovieEffect.Empty,
            state = DetailMovieState(),
            action = { p ->

            },
            imageUrl = "https://image.tmdb.org/t/p/original/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
            title = "Deadpool & Wolverine ",
            genreList = listOf(28),
            rating = 7.9F,
            ratingCount = 1886,
            release = "05-12-2001",
            isAdult = true,
            summary = "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
            id = 1
        )
    }
}