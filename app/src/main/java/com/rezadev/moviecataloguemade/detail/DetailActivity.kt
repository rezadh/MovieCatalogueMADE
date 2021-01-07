package com.rezadev.moviecataloguemade.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rezadev.moviecataloguemade.R
import com.rezadev.core.domain.model.Movie
import com.rezadev.core.domain.model.MovieTv
import com.rezadev.moviecataloguemade.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_MOVIE_TV = "extra_movie_TV"
    }

    private val detailMoviesViewModel: DetailMoviesViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        val movieTv = intent.getParcelableExtra<MovieTv>(EXTRA_MOVIE_TV)
        if(movie != null){
            showDetailMovie(movie)
        }
        if(movieTv != null){
            showDetailMovieTv(movieTv)
        }
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            binding.content.textTitle.text = detailMovie.title
            binding.content.textDate.text = detailMovie.releaseDate
            binding.content.rtDetailRating.rating = detailMovie.getRating()
            binding.content.textRating.text = ("(${detailMovie.voteAverage})")
            binding.content.tvOverview.text = detailMovie.overview
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500${detailMovie.imagePath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(binding.content.imagePoster)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.content.fab.setOnClickListener{
                if(statusFavorite){
                    Toast.makeText(this, "Remove From Favorite", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Add To Favorite", Toast.LENGTH_SHORT).show()
                }
                statusFavorite = !statusFavorite
                detailMoviesViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun showDetailMovieTv(detailMovieTv: MovieTv?) {
        detailMovieTv?.let {
            supportActionBar?.title = detailMovieTv.title
            binding.content.textTitle.text = detailMovieTv.title
            binding.content.textDate.text = detailMovieTv.releaseDate
            binding.content.rtDetailRating.rating = detailMovieTv.getRating()
            binding.content.textRating.text = ("(${detailMovieTv.voteAverage})")
            binding.content.tvOverview.text = detailMovieTv.overview
            var statusFavorite = detailMovieTv.isFavorite
            setStatusFavorite(statusFavorite)
            binding.content.fab.setOnClickListener {
                if (statusFavorite){
                    Toast.makeText(this, "Remove From Favorite", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Add To Favorite", Toast.LENGTH_SHORT).show()
                }
                statusFavorite = !statusFavorite
                detailMoviesViewModel.setFavoriteMovieTv(detailMovieTv, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500${detailMovieTv.imagePath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(binding.content.imagePoster)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.content.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.content.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}