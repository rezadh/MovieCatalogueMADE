package com.rezadev.moviecataloguemade.di

import com.rezadev.core.domain.usecase.MovieInteractor
import com.rezadev.core.domain.usecase.MovieUseCase
import com.rezadev.moviecataloguemade.detail.DetailMoviesViewModel
import com.rezadev.moviecataloguemade.movie.MoviesViewModel
import com.rezadev.moviecataloguemade.movietv.MoviesTvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule =  module {
    viewModel { MoviesViewModel(get()) }
    viewModel { MoviesTvViewModel(get()) }
    viewModel { DetailMoviesViewModel(get()) }
}