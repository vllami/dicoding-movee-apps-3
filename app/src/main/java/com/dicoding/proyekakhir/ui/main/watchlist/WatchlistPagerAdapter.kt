@file:Suppress("DEPRECATION")

package com.dicoding.proyekakhir.ui.main.watchlist

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.proyekakhir.R
import com.dicoding.proyekakhir.ui.main.watchlist.movies.MoviesWatchlistFragment
import com.dicoding.proyekakhir.ui.main.watchlist.tv_shows.TvShowsWatchlistFragment

class WatchlistPagerAdapter(private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesWatchlistFragment()
            1 -> TvShowsWatchlistFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence = context.resources.getString(TAB_TITLES[position])

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }

}