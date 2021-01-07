package com.rezadev.moviecataloguemade.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rezadev.core.domain.usecase.MovieUseCase

class MoviesViewModel(movieUseCase: MovieUseCase) : ViewModel(){
    val movie = movieUseCase.getAllMovies().asLiveData()
}