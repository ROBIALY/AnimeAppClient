package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.core.util.callApi
import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto
import edu.robertconstantin.animeappcliient.feature_heroes.data.remote.HeroApi

class HeroRemoteDataSourceImpl(private val heroApi: HeroApi) : IHeroRemoteDataSource {
    override suspend fun getAllHeroes(): Resource<List<HeroDto>> {
        return callApi { heroApi.getAllHeroes() }
    }
}