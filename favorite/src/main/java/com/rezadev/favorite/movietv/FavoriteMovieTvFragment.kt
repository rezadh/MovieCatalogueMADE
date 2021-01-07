package com.rezadev.favorite.movietv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rezadev.core.ui.movietv.MovieTvAdapter
import com.rezadev.favorite.FavoriteViewModel
import com.rezadev.favorite.databinding.FragmentFavoriteMovieTvBinding
import com.rezadev.moviecataloguemade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieTvFragment : Fragment() {
    private val favoriteMovieViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteMovieTvBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentFavoriteMovieTvBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieTvAdapter = MovieTvAdapter()
            movieTvAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE_TV, selectedData)
                startActivity(intent)
            }
            favoriteMovieViewModel.favoriteMovieTv.observe(viewLifecycleOwner, { dataFavoriteMovieTv ->
                movieTvAdapter.setData(dataFavoriteMovieTv)
                binding?.viewEmptyMovietv?.visibility = if (dataFavoriteMovieTv.isNotEmpty()) View.GONE else View.VISIBLE
            })
            with(binding?.rvFavoriteMovietv){
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieTvAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}