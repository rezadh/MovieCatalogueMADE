package com.rezadev.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rezadev.core.data.source.local.entity.MovieEntity
import com.rezadev.core.data.source.local.entity.MovieTvEntity
import com.rezadev.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var observerMovie: Observer<List<MovieEntity>>

    @Mock
    private lateinit var observerMovieTv: Observer<List<MovieTvEntity>>

//    @Mock
//    private lateinit var pagedListMovie: PagedList<MovieEntity>

//    @Mock
//    private lateinit var pagedListMovieTv: PagedList<MovieTvEntity>

    @Before
    fun setUp(){
        viewModel = FavoriteViewModel(movieUseCase)
    }
//    @Test
//    fun getFavoriteMovies() {
//        val movieMutable = Flow<List<MovieEntity>>
////        val dummyMovies = pagedListMovie
//        Mockito.`when`(movieUseCase.getFavoriteMovie()).thenReturn(movieMutable)
////        viewModel.favoriteMovie().observeForever(observerMovie)
////        verify(observerMovie).onChanged(dummyMovies)
//    }
//
//    @Test
//    fun getFavoriteMoviesTv() {
////        val movieMutable = Flow<List<MovieTvEntity>>
////        val dummyMoviesTv = pagedListMovieTv
////        Mockito.`when`(movieUseCase.getFavoriteMovieTv()).thenReturn(movieMutable)
////        viewModel.favoriteMovieTv().observeForever(observerMovieTv)
////        verify(observerMovieTv).onChanged(dummyMoviesTv)
//    }
}