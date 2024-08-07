package com.ndc.moviekuh.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenreChip(
    modifier: Modifier = Modifier,
    text: String
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.primary)
            .padding(4.dp)
    ) {
        Text(
            text = text,
            style = typography.labelSmall.copy(fontSize = 8.sp),
            color = color.onPrimary
        )
    }
}