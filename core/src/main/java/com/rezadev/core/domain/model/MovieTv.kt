package com.rezadev.core.domain.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class MovieTv (
    val movieTvId: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: String,
    val imagePath: String,
    val isFavorite: Boolean
) : Parcelable {
    fun getRating(): Float{
        return if (voteAverage.isNotEmpty()){
            voteAverage.toFloat() / 2
        }else{
            0f
        }
    }
}