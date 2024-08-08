package com.ndc.moviekuh.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ndc.moviekuh.data.source.local.room.dto.FavoriteDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = FavoriteDto::class
    )
    suspend fun addToFavorite(favoriteDto: FavoriteDto)

    @Query("DELETE FROM tb_favorite_movie WHERE id = :movieId")
    suspend fun deleteById(movieId: Int): Int

    @Query("SELECT * FROM tb_favorite_movie")
    fun getAllFavoriteList(): Flow<List<FavoriteDto>>

    @Query("SELECT EXISTS(SELECT 1 FROM tb_favorite_movie WHERE id = :movieId)")
    suspend fun isFavorite(movieId: Int): Boolean
}