package com.ndc.moviekuh.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
fun MyProductCard(
    modifier: Modifier = Modifier,
    movieImage: String,
    movieName: String,
    movieRating: Double,
    onClick: () -> Unit = {}
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(color.primaryContainer)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = movieImage,
            contentDescription = "",
            error = painterResource(id = R.drawable.error_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(67.dp),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.7f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = movieName,
                    style = typography.titleSmall,
                    color = color.onPrimaryContainer,
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
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                contentDescription = "",
                tint = color.onPrimaryContainer,
                modifier = Modifier
                    .weight(0.2f)
            )
        }
    }
}

@Preview
@Composable
fun MyProductCardPreview() {
    MovieKuhTheme {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(5) {
                MyProductCard(
                    movieImage = "https://image.tmdb.org/t/p/original/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
                    movieName = "Deadpool & Wolverine",
                    movieRating = 7.9,
                )
            }
        }
    }
}