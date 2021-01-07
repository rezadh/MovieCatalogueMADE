package com.rezadev.core.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rezadev.core.data.source.local.LocalDataSource
import com.rezadev.core.data.source.local.entity.MovieEntity
import com.rezadev.core.data.source.local.entity.MovieTvEntity
import com.rezadev.core.data.source.remote.RemoteDataSource
import com.rezadev.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@Suppress("UNCHECKED_CAST")
class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    @Test
    fun getMoviesList() {
        val dataSourceFactory = Mockito.mock(Flow::class.java) as Flow<List<MovieEntity>>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()
    }


    @Test
    fun getMovieTvList() {
        val dataSourceFactory = Mockito.mock(Flow::class.java) as Flow<List<MovieTvEntity>>
        Mockito.`when`(local.getAllMoviesTv()).thenReturn(dataSourceFactory)
        movieRepository.getAllMoviesTv()
    }

    @Test
    fun getFavoriteMovieList() {
        val dataSourceFactory = Mockito.mock(Flow::class.java) as Flow<List<MovieEntity>>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovie()
    }

    @Test
    fun getFavoriteMovieTvList() {
        val dataSourceFactory = Mockito.mock(Flow::class.java) as Flow<List<MovieTvEntity>>
        Mockito.`when`(local.getFavoriteMoviesTv()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovieTv()
    }

}