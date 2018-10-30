package br.com.douglas.tvshow.di.module

import br.com.douglas.tvshow.data.network.factory.api.API
import br.com.douglas.tvshow.di.annotation.AppToken
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

//    @Singleton
//    @Provides
//    fun provideAuthenticateRepository(apiService: API, @AppToken token: String): AuthenticateDataSource {
//        return AuthenticateRepository(apiService, token)
//    }
//
//    @Provides
//    fun provideUserRepository(apiService: API, @AppToken token: String)
//            : UserDataSource {
//        return UserRepository(apiService, token)
//    }
}