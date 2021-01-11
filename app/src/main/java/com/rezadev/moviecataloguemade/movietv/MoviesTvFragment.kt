package com.rezadev.moviecataloguemade.movietv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rezadev.moviecataloguemade.R
import com.rezadev.core.data.source.Resource
import com.rezadev.core.ui.movietv.MovieTvAdapter
import com.rezadev.moviecataloguemade.databinding.FragmentMoviesTvBinding
import com.rezadev.moviecataloguemade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesTvFragment : Fragment() {
    private val movieTveViewModel: MoviesTvViewModel by viewModel()

    private var _binding: FragmentMoviesTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesTvBinding.inflate(inflater, container, false)
        return binding.root
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
            movieTveViewModel.movieTv.observe(viewLifecycleOwner, { movieTv ->
                if(movieTv != null) {
                    when (movieTv) {
                        is Resource.Loading -> binding.progressBarTv.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBarTv.visibility = View.GONE
                            movieTvAdapter.setData(movieTv.data)
                        }
                        is Resource.Error -> {
                            binding.progressBarTv.visibility = View.GONE
                            binding.viewErrorMovietv.root.visibility = View.VISIBLE
                            binding.viewErrorMovietv.tvError.text =
                                    movieTv.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })
            with(binding.rvMoviesTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieTvAdapter
            }
        }
    }
}