package com.dicoding.proyekakhir.ui.details

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.proyekakhir.R.drawable
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity
import com.dicoding.proyekakhir.databinding.ActivityDetailsBinding
import com.dicoding.proyekakhir.databinding.ActivityDetailsBinding.inflate
import com.dicoding.proyekakhir.databinding.ContentDetailsBinding
import com.dicoding.proyekakhir.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import com.dicoding.proyekakhir.vo.Status
import jp.wasabeef.glide.transformations.BlurTransformation

class DetailsActivity : AppCompatActivity() {

    private lateinit var activityDetailsBinding: ActivityDetailsBinding
    private lateinit var contentDetailsBinding: ContentDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailsBinding = inflate(layoutInflater)
        with(activityDetailsBinding) {
            setContentView(root)
            contentDetailsBinding = details
        }

        detailsViewModel = ViewModelProvider(this, getViewModelFactory(this))[DetailsViewModel::class.java]

        val receiveData = intent.getIntExtra(EXTRA_DETAILS, 0)
        val filmChoose = intent.getStringExtra(EXTRA_SELECTED)
        if (receiveData != 0 && filmChoose != null) {
            when (filmChoose) {
                "MOVIE" -> getMoviesData(receiveData)
                "TV_SHOWS" -> getTvShowsData(receiveData)
            }
        }

        setWatchlist()

        val window: Window = window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun getMoviesData(id: Int) {
        detailsViewModel.setMoviesData(id).observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    showControl(true)
                }
                Status.SUCCESS -> {
                    if (it.data != null) {
                        showControl(false)
                        generateMoviesDetails(it.data)
                    }
                }
                Status.ERROR -> {
                    showControl(false)
                    Toast.makeText(applicationContext, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getTvShowsData(id: Int) {
        detailsViewModel.setTvShowsData(id).observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    showControl(true)
                }
                Status.SUCCESS -> {
                    if (it.data != null) {
                        showControl(false)
                        generateTvShowsDetails(it.data)
                    }
                }
                Status.ERROR -> {
                    showControl(false)
                    Toast.makeText(applicationContext, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun generateMoviesDetails(moviesEntity: MoviesEntity) {
        contentDetailsBinding.apply {
            moviesEntity.apply {
                Glide
                    .with(this@DetailsActivity)
                    .load("https://image.tmdb.org/t/p/w500$backdrop")
                    .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(3, 6)))
                    .into(imgBackdrop)
                Glide
                    .with(this@DetailsActivity)
                    .load("https://image.tmdb.org/t/p/w500$poster")
                    .transform(RoundedCorners(36))
                    .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                    .into(imgPoster)
                tvTitle.text = title
                tvRating.text = rating.toString()
                tvReleaseDate.text = releaseDate
                tvSynopsis.text = synopsis
            }
        }
    }

    private fun generateTvShowsDetails(tvShowsEntity: TvShowsEntity) {
        contentDetailsBinding.apply {
            tvShowsEntity.apply {
                Glide
                    .with(this@DetailsActivity)
                    .load("https://image.tmdb.org/t/p/w500$backdrop")
                    .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(3, 6)))
                    .into(imgBackdrop)
                Glide
                    .with(this@DetailsActivity)
                    .load("https://image.tmdb.org/t/p/w500$poster")
                    .transform(RoundedCorners(36))
                    .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                    .into(imgPoster)
                tvTitle.text = title
                tvRating.text = rating.toString()
                tvReleaseDate.text = releaseDate
                tvSynopsis.text = synopsis
            }
        }
    }

    private fun setWatchlist() {
        val selected = intent.getStringExtra(EXTRA_SELECTED)

        if (selected != null) {
            contentDetailsBinding.btnWatchlist.setOnClickListener {
                when (selected) {
                    "MOVIES" -> detailsViewModel.setMoviesWatchlist()
                    "TV_SHOWS" -> detailsViewModel.setTvShowsWatchlist()
                }
            }
        }
    }

    private fun showControl(state: Boolean) {
        contentDetailsBinding.apply {
            when {
                state -> {
                    star.visibility = View.GONE
                    releaseDate.visibility = View.GONE
                    synopsis.visibility = View.GONE
                    loading.visibility = View.VISIBLE
                }
                else -> {
                    star.visibility = View.VISIBLE
                    releaseDate.visibility = View.VISIBLE
                    synopsis.visibility = View.VISIBLE
                    loading.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        const val EXTRA_DETAILS = "extra_details"
        const val EXTRA_SELECTED = "extra_selected"
    }

}