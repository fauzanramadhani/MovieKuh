package com.ndc.moviekuh.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ndc.moviekuh.data.source.local.room.dto.FavoriteDto

@Database(entities = [FavoriteDto::class], version = 1)
abstract class MovieKuhDatabase : RoomDatabase() {
    //    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: MovieKuhDatabase? = null

        fun getDatabase(context: Context): MovieKuhDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieKuhDatabase::class.java,
                    "db_moviekuh"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
