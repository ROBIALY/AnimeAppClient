package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.core.util.callApi
import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto
import javax.inject.Inject

class HeroRemoteDataSourceImpl @Inject constructor(private val heroApi: HeroApi) : IHeroRemoteDataSource {
    override suspend fun getAllHeroes(): Resource<List<HeroDto>> {
        return callApi { heroApi.getAllHeroes() }
    }
}