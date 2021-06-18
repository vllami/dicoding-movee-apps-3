package com.dicoding.proyekakhir.ui.main.movies

import android.content.Intent
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.proyekakhir.R.drawable
import com.dicoding.proyekakhir.core.Constant.IMAGE_BASE_URL
import com.dicoding.proyekakhir.core.Constant.ITEMS_POSTER_RADIUS
import com.dicoding.proyekakhir.core.Constant.MOVIES
import com.dicoding.proyekakhir.core.Constant.RADIUS
import com.dicoding.proyekakhir.core.Constant.SAMPLING
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.databinding.ItemListBinding
import com.dicoding.proyekakhir.ui.details.DetailsActivity
import com.dicoding.proyekakhir.ui.details.DetailsActivity.Companion.EXTRA_DETAILS
import com.dicoding.proyekakhir.ui.details.DetailsActivity.Companion.EXTRA_SELECTED
import jp.wasabeef.glide.transformations.BlurTransformation
import android.view.LayoutInflater.from as inflateFrom
import com.dicoding.proyekakhir.databinding.ItemListBinding.inflate as ItemListBinding

class MoviesAdapter : PagedListAdapter<MoviesEntity, MoviesAdapter.MoviesViewHolder>(DIFF_UTIL_CALLBACK) {

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position).also {
            if (it != null) holder.bind(it)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesViewHolder {
        ItemListBinding(inflateFrom(viewGroup.context), viewGroup, false).also {
            return MoviesViewHolder(it)
        }
    }

    inner class MoviesViewHolder(private val itemListBinding: ItemListBinding) : RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(moviesEntity: MoviesEntity) {
            itemListBinding.apply {
                moviesEntity.apply {
                    itemView.apply {
                        Glide
                            .with(context)
                            .load("$IMAGE_BASE_URL$backdrop")
                            .apply(placeholderOf(drawable.bg_loading_backdrop).error(drawable.bg_loading_poster))
                            .apply(bitmapTransform(BlurTransformation(RADIUS, SAMPLING)))
                            .into(imgBackdrop)
                        Glide
                            .with(context)
                            .load("$IMAGE_BASE_URL$poster")
                            .transform(RoundedCorners(ITEMS_POSTER_RADIUS))
                            .apply(placeholderOf(drawable.bg_loading_poster).error(drawable.bg_loading_poster))
                            .into(imgPoster)
                        tvTitle.text = title
                        tvRating.text = rating.toString()
                        tvReleaseDate.text = releaseDate
                        tvSynopsis.text = synopsis

                        setOnClickListener {
                            Intent(context, DetailsActivity::class.java).also {
                                it.putExtra(EXTRA_DETAILS, moviesEntity.id)
                                it.putExtra(EXTRA_SELECTED, MOVIES)
                                context.startActivity(it)
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIL_CALLBACK = object : ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}