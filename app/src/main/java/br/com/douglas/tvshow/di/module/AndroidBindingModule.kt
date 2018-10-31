package br.com.douglas.tvshow.di.module

import br.com.douglas.tvshow.di.annotation.ActivityScope
import br.com.douglas.tvshow.di.annotation.FragmentScope
import br.com.douglas.tvshow.ui.home.HomeFragment
import br.com.douglas.tvshow.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class AndroidBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
}