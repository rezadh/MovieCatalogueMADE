package com.rezadev.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.rezadev.core.ui.movie.MovieAdapter
import com.rezadev.favorite.FavoriteViewModel
import com.rezadev.favorite.databinding.FragmentFavoriteMovieBinding
import com.rezadev.moviecataloguemade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {
    private val favoriteMovieViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }
            favoriteMovieViewModel.favoriteMovie.observe(viewLifecycleOwner, { dataFavoriteMovie ->
                movieAdapter.setData(dataFavoriteMovie)
                binding?.viewEmpty?.visibility  = if (dataFavoriteMovie.isNotEmpty()) View.GONE else View.VISIBLE
            })
            with(binding?.rvFavoriteMovies){
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}