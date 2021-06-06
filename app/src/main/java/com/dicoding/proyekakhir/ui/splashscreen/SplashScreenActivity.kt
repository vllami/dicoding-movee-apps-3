package com.dicoding.proyekakhir.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.proyekakhir.R.layout
import com.dicoding.proyekakhir.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_SCREEN_DELAY = 3_000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, SPLASH_SCREEN_DELAY.toLong())
    }

}