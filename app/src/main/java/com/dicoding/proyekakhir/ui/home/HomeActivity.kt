package com.dicoding.proyekakhir.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.proyekakhir.ui.home.fab_watchlist.WatchlistActivity
import com.dicoding.proyekakhir.databinding.ActivityHomeBinding.inflate as ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityHomeBinding(layoutInflater).also { activityHomeBinding ->
            with(activityHomeBinding) {
                setContentView(root)

                HomePagerAdapter(this@HomeActivity, supportFragmentManager).also {
                    with(viewPager) {
                        this.adapter = it
                        tabs.setupWithViewPager(this)
                    }
                }

                btnWatchlist.setOnClickListener {
                    startActivity(Intent(this@HomeActivity, WatchlistActivity::class.java))
                }
            }
        }
    }

}