package com.dicoding.proyekakhir.ui.details

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.proyekakhir.R.drawable
import com.dicoding.proyekakhir.R.string
import com.dicoding.proyekakhir.core.Constant.DETAILS_POSTER_RADIUS
import com.dicoding.proyekakhir.core.Constant.IMAGE_BASE_URL
import com.dicoding.proyekakhir.core.Constant.MOVIES
import com.dicoding.proyekakhir.core.Constant.RADIUS
import com.dicoding.proyekakhir.core.Constant.SAMPLING
import com.dicoding.proyekakhir.core.Constant.TV_SHOWS
import com.dicoding.proyekakhir.core.enums.Status.*
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import com.dicoding.proyekakhir.core.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import com.dicoding.proyekakhir.databinding.ActivityDetailsBinding
import com.dicoding.proyekakhir.databinding.ActivityDetailsBinding.inflate
import jp.wasabeef.glide.transformations.BlurTransformation
import android.widget.Toast.makeText as Toast

class DetailsActivity : AppCompatActivity() {

    private lateinit var activityDetailsBinding: ActivityDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailsBinding = inflate(layoutInflater)
        setContentView(activityDetailsBinding.root)

        detailsViewModel = ViewModelProvider(this, getViewModelFactory(this))[DetailsViewModel::class.java]

        intent.apply {
            val details = getIntExtra(EXTRA_DETAILS, 0)
            val selected = getStringExtra(EXTRA_SELECTED)

            if (details != 0 && selected != null) {
                when (selected) {
                    MOVIES -> getMoviesData(details)
                    TV_SHOWS -> getTvShowsData(details)
                }
            }
        }

        setWatchlist()

        window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
    }

    private fun getMoviesData(moviesID: Int) {
        detailsViewModel.setMoviesData(moviesID).observe(this, {
            when (it.status) {
                LOADING -> {
                    showControl(true)
                }
                SUCCESS -> {
                    with(it.data) {
                        if (this != null) {
                            showControl(false)
                            generateMoviesDetails(this)
                        }
                    }
                }
                ERROR -> {
                    showControl(false)
                    Toast(this@DetailsActivity, resources.getString(string.failed_load), LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getTvShowsData(tvShowsID: Int) {
        detailsViewModel.setTvShowsData(tvShowsID).observe(this, {
            when (it.status) {
                LOADING -> {
                    showControl(true)
                }
                SUCCESS -> {
                    with(it.data) {
                        if (this != null) {
                            showControl(false)
                            generateTvShowsDetails(this)
                        }
                    }
                }
                ERROR -> {
                    showControl(false)
                    Toast(this@DetailsActivity, resources.getString(string.failed_load), LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun generateMoviesDetails(moviesEntity: MoviesEntity) {
        activityDetailsBinding.apply {
            moviesEntity.apply {
                Glide
                    .with(this@DetailsActivity)
                    .load("$IMAGE_BASE_URL$backdrop")
                    .apply(placeholderOf(drawable.bg_loading_backdrop).error(drawable.bg_loading_poster))
                    .apply(bitmapTransform(BlurTransformation(RADIUS, SAMPLING)))
                    .into(imgBackdrop)
                Glide
                    .with(this@DetailsActivity)
                    .load("$IMAGE_BASE_URL$poster")
                    .transform(RoundedCorners(DETAILS_POSTER_RADIUS))
                    .apply(placeholderOf(drawable.bg_loading_poster).error(drawable.bg_loading_poster))
                    .into(imgPoster)
                tvTitle.text = title
                tvRating.text = rating.toString()
                tvReleaseDate.text = releaseDate
                tvSynopsis.text = synopsis
                btnWatchlist.isChecked = watchlist
            }
        }
    }

    private fun generateTvShowsDetails(tvShowsEntity: TvShowsEntity) {
        activityDetailsBinding.apply {
            tvShowsEntity.apply {
                Glide
                    .with(this@DetailsActivity)
                    .load("$IMAGE_BASE_URL$backdrop")
                    .apply(placeholderOf(drawable.bg_loading_backdrop))
                    .apply(bitmapTransform(BlurTransformation(RADIUS, SAMPLING)))
                    .into(imgBackdrop)
                Glide
                    .with(this@DetailsActivity)
                    .load("$IMAGE_BASE_URL$poster")
                    .transform(RoundedCorners(DETAILS_POSTER_RADIUS))
                    .apply(placeholderOf(drawable.bg_loading_poster))
                    .into(imgPoster)
                tvTitle.text = title
                tvRating.text = rating.toString()
                tvReleaseDate.text = releaseDate
                tvSynopsis.text = synopsis
                btnWatchlist.isChecked = watchlist
            }
        }
    }

    private fun setWatchlist() {
        val selected = intent.getStringExtra(EXTRA_SELECTED)

        if (selected != null) {
            activityDetailsBinding.btnWatchlist.setOnClickListener {
                detailsViewModel.apply {
                    when (selected) {
                        MOVIES -> setMoviesWatchlist()
                        TV_SHOWS -> setTvShowsWatchlist()
                    }
                }
            }
        }
    }

    private fun showControl(state: Boolean) {
        activityDetailsBinding.apply {
            when {
                state -> {
                    releaseDate.visibility = GONE
                    synopsis.visibility = GONE
                    loading.visibility = VISIBLE
                }
                else -> {
                    releaseDate.visibility = VISIBLE
                    synopsis.visibility = VISIBLE
                    loading.visibility = GONE
                }
            }
        }
    }

    companion object {
        const val EXTRA_DETAILS = "extra_details"
        const val EXTRA_SELECTED = "extra_selected"
    }

}