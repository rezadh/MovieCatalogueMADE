package com.rezadev.core.domain.repository

import com.rezadev.core.data.source.Resource
import com.rezadev.core.domain.model.Movie
import com.rezadev.core.domain.model.MovieTv
import kotlinx.coroutines.flow.Flow


interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getAllMoviesTv(): Flow<Resource<List<MovieTv>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun getFavoriteMovieTv(): Flow<List<MovieTv>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun setFavoriteMovieTv(movieTv: MovieTv, state: Boolean)

}