package br.com.douglas.tvshow.di.module

import android.arch.lifecycle.ViewModelProvider
import br.com.douglas.tvshow.base.MyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun provideViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

}