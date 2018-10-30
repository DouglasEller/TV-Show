package br.com.douglas.tvshow

import android.app.Activity
import android.app.Application
import android.content.Context
import br.com.douglas.tvshow.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TVShowApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

//    @Inject
//    lateinit var preferenceHelper: PreferenceHelper

    init {
        instance = this
    }

    companion object {
        private var instance: TVShowApplication? = null

        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}