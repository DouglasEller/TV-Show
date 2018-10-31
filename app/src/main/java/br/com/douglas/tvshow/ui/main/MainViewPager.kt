package br.com.douglas.tvshow.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.douglas.tvshow.ui.favorite.FavoriteFragment
import br.com.douglas.tvshow.ui.home.HomeFragment


class MainViewPager internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val PAGE_COUNT = 2

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = FavoriteFragment()
        }

        return fragment
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }
}