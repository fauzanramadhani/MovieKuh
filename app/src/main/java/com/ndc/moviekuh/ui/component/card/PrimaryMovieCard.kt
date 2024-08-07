package com.ndc.moviekuh.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ndc.moviekuh.R
import com.ndc.moviekuh.ui.theme.MovieKuhTheme

@Composable
fun PrimaryMovieCard(
    modifier: Modifier = Modifier,
    movieImage: String,
    movieName: String,
    movieRating: Double,
    onClick: () -> Unit = {}
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = color.outline,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        AsyncImage(
            model = movieImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.error_image),
            modifier = Modifier
                .height(158.dp)
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .padding(
                    top = 150.dp,
                ),
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(color.background)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = movieName,
                    style = typography.labelMedium,
                    color = color.onBackground,
                    maxLines = 2,
                    minLines = 2
                )
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
                        text = movieRating.toString(),
                        style = typography.bodySmall,
                        color = color.primary,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PrimaryCardPreview() {
    MovieKuhTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                PrimaryMovieCard(
                    movieImage = "https://image.tmdb.org/t/p/original/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
                    movieName = "Deadpool & Wolverine",
                    movieRating = 7.9,
                )
            }
        }
    }
}