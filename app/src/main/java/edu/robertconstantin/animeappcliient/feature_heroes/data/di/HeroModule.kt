package edu.robertconstantin.animeappcliient.feature_heroes.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroApi
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroApi.Companion.HERO_BASE_URL
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroRemoteDataSourceImpl
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.IHeroRemoteDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.repository.HeroRepositoryImpl
import edu.robertconstantin.animeappcliient.feature_heroes.domain.GetAllHeroesUseCase
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): HeroApi {
        return Retrofit.Builder()
            .baseUrl(HERO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(HeroApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroRemoteDataSource(api: HeroApi): IHeroRemoteDataSource {
        return HeroRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideRepository(remote: IHeroRemoteDataSource): IHeroRepository {
        return HeroRepositoryImpl(remote)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: IHeroRepository): GetAllHeroesUseCase {
        return GetAllHeroesUseCase(repository)
    }

}