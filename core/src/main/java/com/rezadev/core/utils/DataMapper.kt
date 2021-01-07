package com.rezadev.core.utils

import com.rezadev.core.data.source.local.entity.MovieEntity
import com.rezadev.core.data.source.local.entity.MovieTvEntity
import com.rezadev.core.data.source.remote.response.MovieResponse
import com.rezadev.core.data.source.remote.response.MovieTvResponse
import com.rezadev.core.domain.model.Movie
import com.rezadev.core.domain.model.MovieTv

object DataMapper {
    fun mapResponsesToEntitiesMovie(input: List<MovieResponse>) : List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                imagePath = it.imagePath,
                false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponsesToEntitiesMovieTv(input: List<MovieTvResponse>) : List<MovieTvEntity> {
        val movieTvList = ArrayList<MovieTvEntity>()
        input.map {
            val movie = MovieTvEntity(
                movieTvId = it.movieTvId,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                imagePath = it.imagePath,
                false
            )
            movieTvList.add(movie)
        }
        return movieTvList
    }

    fun mapEntitiesToDomainMovie(input: List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                imagePath = it.imagePath,
                false
            )
        }

    fun mapEntitiesToDomainMovieTv(input: List<MovieTvEntity>) : List<MovieTv> =
        input.map {
            MovieTv(
                movieTvId = it.movieTvId,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                imagePath = it.imagePath,
                false
            )
        }

    fun mapDomainToEntityMovie(input: Movie) = MovieEntity (
        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        imagePath = input.imagePath,
        isFavorite = input.isFavorite
    )

    fun mapDomainToEntityMovieTv(input: MovieTv) = MovieTvEntity (
        movieTvId = input.movieTvId,
        title = input.title,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        imagePath = input.imagePath,
        isFavorite = input.isFavorite
    )
}