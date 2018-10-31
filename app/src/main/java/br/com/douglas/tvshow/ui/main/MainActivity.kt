package br.com.douglas.tvshow.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
        ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bn_main.setOnNavigationItemSelectedListener(this)

        vp_main.adapter = MainViewPager(supportFragmentManager)
        vp_main.offscreenPageLimit = 1
        vp_main.addOnPageChangeListener(this)
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(position: Int) {
        bn_main.menu.getItem(position).isChecked = true
    }

    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onNavigationItemSelected(position: MenuItem): Boolean {
        if (position.itemId == R.id.nav_home)
            vp_main.currentItem = 0
        else
            vp_main.currentItem = 1
        return true
    }
}
