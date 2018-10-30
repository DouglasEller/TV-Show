package br.com.douglas.tvshow.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.douglas.tvshow.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        ViewPager.OnPageChangeListener,
        //BottomNavigationView.OnNavigationItemReselectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bn_main.setOnNavigationItemSelectedListener(this)

        vp_main.adapter = MainViewPager(supportFragmentManager)
        vp_main.addOnPageChangeListener(this)
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(p0: Int) {
    }

    override fun onPageScrollStateChanged(position: Int) {
        //bn_main.focusable = position
    }

    override fun onNavigationItemSelected(position: MenuItem): Boolean {
        vp_main.currentItem = position.itemId
        return true
    }
}
