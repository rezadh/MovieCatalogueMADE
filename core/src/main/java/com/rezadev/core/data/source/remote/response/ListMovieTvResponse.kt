package com.rezadev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieTvResponse (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("results")
    val result: List<MovieTvResponse>
)