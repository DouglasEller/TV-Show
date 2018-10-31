package br.com.douglas.tvshow.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
        HasSupportFragmentInjector,
        ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewModel: MainViewModel

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

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
