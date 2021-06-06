package com.dicoding.proyekakhir.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.proyekakhir.R.string
import com.dicoding.proyekakhir.ui.home.tab_movies.MoviesFragment
import com.dicoding.proyekakhir.ui.home.tab_tv_shows.TvShowsFragment

class HomePagerAdapter(private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment()
            1 -> TvShowsFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(string.movies, string.tv_shows)
    }

}