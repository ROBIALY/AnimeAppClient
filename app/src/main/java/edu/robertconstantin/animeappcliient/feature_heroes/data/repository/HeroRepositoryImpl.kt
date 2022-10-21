package edu.robertconstantin.animeappcliient.feature_heroes.data.repository

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote.IHeroRemoteDataSource
import edu.robertconstantin.animeappcliient.feature_heroes.data.mapper.toHeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(private val remoteHeroDataSource: IHeroRemoteDataSource): IHeroRepository {

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
}