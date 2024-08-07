package com.ndc.moviekuh.data.source.local.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
