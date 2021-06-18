package com.dicoding.proyekakhir.ui.main.watchlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.proyekakhir.databinding.ActivityWatchlistBinding.inflate as ActivityWatchlistBinding

class WatchlistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityWatchlistBinding(layoutInflater).also { binding ->
            binding.apply {
                setContentView(root)

                toolbar.setNavigationOnClickListener {
                    onBackPressed()
                }

                WatchlistPagerAdapter(this@WatchlistActivity, supportFragmentManager).also {
                    viewPager.apply {
                        adapter = it
                        tabs.setupWithViewPager(this)
                    }
                }
            }
        }
    }

}