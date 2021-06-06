package com.dicoding.proyekakhir.ui.home.fab_watchlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.proyekakhir.databinding.ActivityWatchlistBinding

class WatchlistActivity : AppCompatActivity() {

    private lateinit var activityWatchlistBinding: ActivityWatchlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityWatchlistBinding = ActivityWatchlistBinding.inflate(layoutInflater)
        activityWatchlistBinding.apply {
            setContentView(activityWatchlistBinding.root)

            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            WatchlistPagerAdapter(this@WatchlistActivity, supportFragmentManager).also {
                viewPager.adapter = it
                tabs.setupWithViewPager(viewPager)
            }
            Toast.makeText(applicationContext, "Swipe ke kanan atau kiri untuk menghapus", Toast.LENGTH_SHORT).show()
        }
    }

}