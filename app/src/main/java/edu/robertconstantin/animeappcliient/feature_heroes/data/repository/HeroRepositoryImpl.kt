package edu.robertconstantin.animeappcliient.feature_heroes.data.repository

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local.IHeroLocalDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.IHeroRemoteDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.mapper.toFavHeroEntity
import edu.robertconstantin.animeappcliient.feature_heroes.data.mapper.toHeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val remoteHeroDataSource: IHeroRemoteDataSource,
    private val localHeroDataSource: IHeroLocalDataSource
    ): IHeroRepository {

    override suspend fun getAllHeroes(): Resource<List<HeroDM>> {
        return remoteHeroDataSource.getAllHeroes().mapResourceData(
            success = { heroes ->
                Resource.Success(heroes?.map { it.toHeroDM() })
            },
            error = { msg ->
                Resource.Error(msg ?: UiText.unknownError())
            }
        )
    }

    override suspend fun insertFavoriteHero(heroDM: HeroDM) {
        localHeroDataSource.insertFavoriteHero(heroDM.toFavHeroEntity())
    }

    override suspend fun deleteFavoriteHero(heroDM: HeroDM): Int {
        return localHeroDataSource.deleteFavoriteHero(heroDM.toFavHeroEntity())
    }

    override fun getAllFavoritesHeroes(): Flow<List<HeroDM>> {
        return localHeroDataSource.getAllFavoritesHeroes().map { heroes ->
            heroes.map { hero -> hero.toHeroDM() }
        }
    }

    override fun getHeroById(heroId: Int): Flow<HeroDM> {
        return localHeroDataSource.getHeroById(heroId).map { it.toHeroDM() }
    }

    override suspend fun updateFavoriteHero(
        name: String,
        about: String,
        power: String,
        id: Int
    ) {
        localHeroDataSource.updateFavoriteHero(name, about, power, id)
    }

}