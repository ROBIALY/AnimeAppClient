package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto

interface IHeroRemoteDataSource {
    suspend fun getAllHeroes(): Resource<List<HeroDto>>
}