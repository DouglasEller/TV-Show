package br.com.douglas.tvshow.di.module


import br.com.douglas.tvshow.network.api.API
import br.com.douglas.tvshow.network.repository.TVShowsDataSource
import br.com.douglas.tvshow.network.repository.TVShowsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    //    @Singleton
//    @Provides
//    fun provideAuthenticateRepository(apiService: API, @AppToken token: String): AuthenticateDataSource {
//        return AuthenticateRepository(apiService, token)
//    }
//
    @Provides
    fun provideTVShowRepository(apiService: API)
            : TVShowsDataSource {
        return TVShowsRepository(apiService)
    }
}