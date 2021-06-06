package com.dicoding.proyekakhir.ui.home.tab_tv_shows

import android.content.Intent
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.proyekakhir.R.drawable
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.databinding.ItemsTvShowsBinding
import com.dicoding.proyekakhir.ui.details.DetailsActivity
import jp.wasabeef.glide.transformations.BlurTransformation
import com.dicoding.proyekakhir.databinding.ItemsTvShowsBinding.inflate as ItemsTvShowsBinding

class TvShowsAdapter : PagedListAdapter<TvShowsEntity, TvShowsAdapter.TvShowsViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        getItem(position).also {
            if (it != null) holder.bind(it)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TvShowsViewHolder {
        ItemsTvShowsBinding(from(viewGroup.context), viewGroup, false).also {
            return TvShowsViewHolder(it)
        }
    }

    inner class TvShowsViewHolder(private val binding: ItemsTvShowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entityOfTvShows: TvShowsEntity) {
            binding.apply {
                entityOfTvShows.apply {
                    Glide
                        .with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500$backdrop")
                        .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                        .apply(RequestOptions.bitmapTransform(BlurTransformation(3, 6)))
                        .into(imgBackdrop)
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500$poster")
                        .transform(RoundedCorners(18))
                        .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                        .into(imgPoster)
                    tvTitle.text = title
                    tvRating.text = rating.toString()
                    tvReleaseDate.text = releaseDate
                    tvSynopsis.text = synopsis

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