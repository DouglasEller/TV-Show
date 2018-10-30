package br.com.douglas.tvshow.di.module

import android.content.Context
import android.util.Log
import br.com.douglas.tvshow.BuildConfig
import br.com.douglas.tvshow.TVShowApplication
import br.com.douglas.tvshow.data.network.factory.LiveDataCallAdapterFactory
import br.com.douglas.tvshow.data.network.factory.api.API
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: TVShowApplication): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("Retrofit", message)
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(): Executor {
        return Executors.newFixedThreadPool(3)
    }

//    @Provides
//    @PreferenceInfo
//    fun providePreferenceName(): String {
//        return BuildConfig.APP_PREF
//    }

//    @Provides
//    @Singleton
//    fun providePreference(context: Context, @PreferenceInfo prefName: String): PreferenceHelper {
//        return PreferenceHelper(context, prefName)
//    }

//    @Provides
//    @AppToken
//    fun provideAppToken(context: Context, @PreferenceInfo prefName: String): String {
//        return PreferenceHelper(context, prefName).getAppToken()
//    }
}
