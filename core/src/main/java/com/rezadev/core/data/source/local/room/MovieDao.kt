package com.rezadev.core.data.source.local.room

import androidx.room.*
import com.rezadev.core.data.source.local.entity.MovieEntity
import com.rezadev.core.data.source.local.entity.MovieTvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM moviesTv_table")
    fun getAllMoviesTv(): Flow<List<MovieTvEntity>>

    @Query("SELECT * FROM movies_table where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM moviesTv_table where isFavorite = 1")
    fun getFavoriteTMoviesTv(): Flow<List<MovieTvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesTv(moviesTv: List<MovieTvEntity>)

    @Update
    fun updateFavoriteMovies(movies: MovieEntity)

    @Update
    fun updateFavoriteMovieTv(moviesTv: MovieTvEntity)
}