package edu.robertconstantin.animeappcliient.feature_heroes.data.di

import android.app.Application
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local.HeroLocalDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local.IHeroLocalDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroApi
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroApi.Companion.HERO_BASE_URL
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.HeroRemoteDataSourceImpl
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.IHeroRemoteDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.local_db.FavoriteHeroDatabase
import edu.robertconstantin.animeappcliient.feature_heroes.data.repository.HeroRepositoryImpl
import edu.robertconstantin.animeappcliient.feature_heroes.data.util.DataConst.FAVORITES_HERO_DB
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.*
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
    fun providesHeroDatabase(app: Application): FavoriteHeroDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteHeroDatabase::class.java,
            FAVORITES_HERO_DB
        ).build()
    }


    @Provides
    @Singleton
    fun provideHeroRemoteDataSource(api: HeroApi): IHeroRemoteDataSource {
        return HeroRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton fun provideHeroLocalDataSource(local: FavoriteHeroDatabase): IHeroLocalDataSource {
        return HeroLocalDataSource(local.dao)
    }

    @Provides
    @Singleton
    fun provideRepository(remote: IHeroRemoteDataSource, local: IHeroLocalDataSource): IHeroRepository {
        return HeroRepositoryImpl(remote, local)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: IHeroRepository): HeroUseCases {
        return HeroUseCases(
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            toggleFavoriteHeroUseCase = ToogleFavoriteHeroUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDeleteFavHeroUseCase(repository: IHeroRepository): DeleteFavoriteHeroUseCase {
        return  DeleteFavoriteHeroUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFavHeroUseCase(repository: IHeroRepository): GetAllFavoritesHeroesUseCase {
        return  GetAllFavoritesHeroesUseCase(repository)
    }



}