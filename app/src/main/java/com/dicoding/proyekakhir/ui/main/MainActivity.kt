package com.dicoding.proyekakhir.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.proyekakhir.R.id.nav_host_fragment
import com.dicoding.proyekakhir.ui.main.watchlist.WatchlistActivity
import com.dicoding.proyekakhir.databinding.ActivityMainBinding.inflate as ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding(layoutInflater).also {
            it.apply {
                setContentView(root)

                toolbar.setNavigationOnClickListener {
                    startActivity(Intent(this@MainActivity, WatchlistActivity::class.java))
                }

                bottomNavigation.apply {
                    itemIconTintList = null

                    setupWithNavController(findNavController(nav_host_fragment))
                }
            }
        }
    }

}