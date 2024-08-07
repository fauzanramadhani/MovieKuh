package com.ndc.moviekuh.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ndc.moviekuh.data.source.local.room.dto.FavoriteDto

@Database(entities = [FavoriteDto::class], version = 1)
abstract class MovieKuhDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao
}
