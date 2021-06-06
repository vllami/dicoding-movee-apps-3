package com.dicoding.proyekakhir.ui.home.fab_watchlist.tab_tv_shows

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
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.databinding.ItemsTvShowsBinding
import com.dicoding.proyekakhir.ui.details.DetailsActivity

class TvShowsWatchlistAdapter : PagedListAdapter<TvShowsEntity, TvShowsWatchlistAdapter.TvShowsWatchlistViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: TvShowsWatchlistViewHolder, position: Int) {
        getItem(position).also {
            if (it != null) holder.bind(it)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TvShowsWatchlistViewHolder {
        ItemsTvShowsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false).also {
            return TvShowsWatchlistViewHolder(it)
        }
    }

    fun getSwiped(bindingAdapterPosition: Int): TvShowsEntity? = getItem(bindingAdapterPosition)

    inner class TvShowsWatchlistViewHolder(private val binding: ItemsTvShowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entityOfTvShows: TvShowsEntity) {
            binding.apply {
                entityOfTvShows.apply {
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
                            it.putExtra(DetailsActivity.EXTRA_DETAILS, entityOfTvShows.id)
                            it.putExtra(DetailsActivity.EXTRA_SELECTED, "TV_SHOW")
                            itemView.context.startActivity(it)
                        }
                    }
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}