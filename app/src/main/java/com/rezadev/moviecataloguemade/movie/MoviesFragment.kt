package com.rezadev.moviecataloguemade.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rezadev.moviecataloguemade.R
import com.rezadev.core.data.source.Resource
import com.rezadev.core.ui.movie.MovieAdapter
import com.rezadev.moviecataloguemade.databinding.FragmentMoviesBinding
import com.rezadev.moviecataloguemade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {
    private val movieViewModel: MoviesViewModel by viewModel()

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
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
            movieViewModel.movie.observe(viewLifecycleOwner, { movie ->
                if (movie != null){
                    when (movie) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                    movie.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })
            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}