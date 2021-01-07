package com.rezadev.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rezadev.core.data.source.local.entity.MovieEntity
import com.rezadev.core.data.source.local.entity.MovieTvEntity

@Database(entities = [MovieEntity::class, MovieTvEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}