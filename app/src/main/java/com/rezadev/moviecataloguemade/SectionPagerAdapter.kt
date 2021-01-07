package com.rezadev.moviecataloguemade

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rezadev.moviecataloguemade.movie.MoviesFragment
import com.rezadev.moviecataloguemade.movietv.MoviesTvFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.movietv)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MoviesFragment()
            1 -> MoviesTvFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(
        TAB_TITLES[position])

    override fun getCount(): Int = 2

}