package com.rezadev.core.domain.usecase

import com.rezadev.core.domain.model.Movie
import com.rezadev.core.domain.model.MovieTv
import com.rezadev.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getAllMoviesTv() = movieRepository.getAllMoviesTv()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun getFavoriteMovieTv() = movieRepository.getFavoriteMovieTv()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun setFavoriteMovieTv(movieTv: MovieTv, state: Boolean) = movieRepository.setFavoriteMovieTv(movieTv, state)

}