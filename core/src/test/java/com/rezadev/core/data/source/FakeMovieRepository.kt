package com.rezadev.core.data.source

import com.rezadev.core.data.source.local.LocalDataSource
import com.rezadev.core.data.source.remote.RemoteDataSource
import com.rezadev.core.data.source.remote.network.ApiResponse
import com.rezadev.core.data.source.remote.response.MovieResponse
import com.rezadev.core.data.source.remote.response.MovieTvResponse
import com.rezadev.core.domain.model.Movie
import com.rezadev.core.domain.model.MovieTv
import com.rezadev.core.domain.repository.IMovieRepository
import com.rezadev.core.utils.AppExecutors
import com.rezadev.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FakeMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutors
) : IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomainMovie(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()
//                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntitiesMovie(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getAllMoviesTv(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<MovieTvResponse>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> {
                return localDataSource.getAllMoviesTv().map {
                    DataMapper.mapEntitiesToDomainMovieTv(it)
                }
            }

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieTvResponse>>> =
                remoteDataSource.getAllMoviesTv()

            override suspend fun saveCallResult(data: List<MovieTvResponse>) {
                val movieTvList = DataMapper.mapResponsesToEntitiesMovieTv(data)
                localDataSource.insertMoviesTv(movieTvList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomainMovie(it)
        }
    }

    override fun getFavoriteMovieTv(): Flow<List<MovieTv>> {
        return localDataSource.getFavoriteMoviesTv().map {
            DataMapper.mapEntitiesToDomainMovieTv(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntityMovie(movie)
        appExecutor.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun setFavoriteMovieTv(movieTv: MovieTv, state: Boolean) {
        val movieTvEntity = DataMapper.mapDomainToEntityMovieTv(movieTv)
        appExecutor.diskIO().execute { localDataSource.setFavoriteMovieTv(movieTvEntity, state) }
    }

}