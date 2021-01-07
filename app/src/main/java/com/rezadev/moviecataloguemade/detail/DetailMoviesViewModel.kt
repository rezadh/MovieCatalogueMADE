package com.rezadev.moviecataloguemade.detail

import androidx.lifecycle.ViewModel
import com.rezadev.core.domain.model.Movie
import com.rezadev.core.domain.model.MovieTv
import com.rezadev.core.domain.usecase.MovieUseCase

class DetailMoviesViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)

    fun setFavoriteMovieTv(movieTv: MovieTv, newStatus: Boolean) =
        movieUseCase.setFavoriteMovieTv(movieTv, newStatus)
}