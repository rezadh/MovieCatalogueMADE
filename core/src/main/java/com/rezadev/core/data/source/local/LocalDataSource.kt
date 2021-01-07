package com.rezadev.core.data.source.local

import com.rezadev.core.data.source.local.entity.MovieEntity
import com.rezadev.core.data.source.local.entity.MovieTvEntity
import com.rezadev.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getAllMoviesTv(): Flow<List<MovieTvEntity>> = movieDao.getAllMoviesTv()

    fun getFavoriteMovies() : Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun getFavoriteMoviesTv() : Flow<List<MovieTvEntity>> = movieDao.getFavoriteTMoviesTv()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    suspend fun insertMoviesTv(movieTvList: List<MovieTvEntity>) = movieDao.insertMoviesTv(movieTvList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovies(movie)
    }
    fun setFavoriteMovieTv(movieTv: MovieTvEntity, newState: Boolean) {
        movieTv.isFavorite = newState
        movieDao.updateFavoriteMovieTv(movieTv)
    }

}