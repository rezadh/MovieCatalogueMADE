package com.rezadev.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rezadev.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()

    val favoriteMovieTv = movieUseCase.getFavoriteMovieTv().asLiveData()
}