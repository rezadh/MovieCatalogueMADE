package com.rezadev.core.data.source.remote

import android.util.Log
import com.rezadev.core.data.source.remote.network.ApiResponse
import com.rezadev.core.data.source.remote.network.ApiService
import com.rezadev.core.data.source.remote.response.MovieResponse
import com.rezadev.core.data.source.remote.response.MovieTvResponse
import com.rezadev.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getPopularMovies()
                EspressoIdlingResource.increment()
                val dataArray = response.result
                EspressoIdlingResource.decrement()
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getAllMoviesTv(): Flow<ApiResponse<List<MovieTvResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getPopularTvShow()
                EspressoIdlingResource.increment()
                val dataArray = response.result
                EspressoIdlingResource.decrement()
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}