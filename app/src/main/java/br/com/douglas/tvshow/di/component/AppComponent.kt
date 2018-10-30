package br.com.douglas.tvshow.di.component

import br.com.douglas.tvshow.TVShowApplication
import br.com.douglas.tvshow.di.module.AndroidBindingModule
import br.com.douglas.tvshow.di.module.AppModule
import br.com.douglas.tvshow.di.module.RepositoryModule
import br.com.douglas.tvshow.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        AndroidBindingModule::class,
        ViewModelModule::class,
        RepositoryModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: TVShowApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: TVShowApplication)
}
