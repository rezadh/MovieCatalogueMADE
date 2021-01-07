package com.rezadev.moviecataloguemade.movietv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rezadev.core.domain.usecase.MovieUseCase

class MoviesTvViewModel(movieUseCase: MovieUseCase) : ViewModel(){
    val movieTv = movieUseCase.getAllMoviesTv().asLiveData()
}