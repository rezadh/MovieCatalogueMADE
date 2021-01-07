package com.rezadev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieTvResponse (

    @field:SerializedName("id")
    val movieTvId: Int,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("first_air_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: String,

    @field:SerializedName("poster_path")
    val imagePath: String
)