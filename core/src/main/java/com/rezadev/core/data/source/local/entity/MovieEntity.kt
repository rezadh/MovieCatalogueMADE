package com.rezadev.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity(tableName = "movies_table")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var movieId: Int = 0,

    @ColumnInfo(name = "name")
    var title: String = "",

    @ColumnInfo(name = "overview")
    var overview: String = "",

    @ColumnInfo(name = "release_date")
    var releaseDate: String = "",

    @ColumnInfo(name = "vote_average")
    var voteAverage: String = "",

    @ColumnInfo(name = "poster_path")
    var imagePath: String = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

): Parcelable