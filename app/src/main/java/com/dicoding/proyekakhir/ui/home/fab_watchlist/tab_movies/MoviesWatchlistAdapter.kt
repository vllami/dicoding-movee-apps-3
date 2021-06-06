package com.dicoding.proyekakhir.ui.home.fab_watchlist.tab_movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.proyekakhir.R
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.databinding.ItemsMoviesBinding
import com.dicoding.proyekakhir.ui.details.DetailsActivity

class MoviesWatchlistAdapter : PagedListAdapter<MoviesEntity, MoviesWatchlistAdapter.MoviesWatchlistViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: MoviesWatchlistViewHolder, position: Int) {
        getItem(position).also {
            if (it != null) holder.bind(it)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesWatchlistViewHolder {
        ItemsMoviesBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false).also {
            return MoviesWatchlistViewHolder(it)
        }
    }

    fun getSwiped(bindingAdapterPosition: Int): MoviesEntity? = getItem(bindingAdapterPosition)

    inner class MoviesWatchlistViewHolder(private val binding: ItemsMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entityOfMovies: MoviesEntity) {
            binding.apply {
                entityOfMovies.apply {
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500$poster")
                        .transform(RoundedCorners(18))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                        .into(imgPoster)
                    tvTitle.text = title
                    tvRating.text = rating.toString()
                    tvReleaseDate.text = releaseDate

                    itemView.setOnClickListener {
                        Intent(itemView.context, DetailsActivity::class.java).also {
                            it.putExtra(DetailsActivity.EXTRA_DETAILS, entityOfMovies.id)
                            it.putExtra(DetailsActivity.EXTRA_SELECTED, "MOVIES")
                            itemView.context.startActivity(it)
                        }
                    }
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}