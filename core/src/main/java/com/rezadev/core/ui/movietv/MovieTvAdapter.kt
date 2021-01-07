package com.rezadev.core.ui.movietv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rezadev.core.R
import com.rezadev.core.databinding.ItemsMovieTvBinding
import com.rezadev.core.domain.model.MovieTv

class MovieTvAdapter : RecyclerView.Adapter<MovieTvAdapter.ListViewHolder>() {
    private var listData = ArrayList<MovieTv>()
    var onItemClick: ((MovieTv) -> Unit)? = null

    fun setData(newListData: List<MovieTv>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_movie_tv, parent, false))


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsMovieTvBinding.bind(itemView)
        fun bind(data : MovieTv) {
            with(binding) {
                tvItemTitle.text = data.title
                rtRating.rating = data.getRating()
                tvItemDate.text = data.releaseDate
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${data.imagePath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .fitCenter()
                    .into(imgPoster)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }


}