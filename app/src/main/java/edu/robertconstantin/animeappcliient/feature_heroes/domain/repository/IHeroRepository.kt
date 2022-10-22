package edu.robertconstantin.animeappcliient.feature_heroes.domain.repository

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import kotlinx.coroutines.flow.Flow

interface IHeroRepository {
    suspend fun getAllHeroes(): Resource<List<HeroDM>>
    suspend fun insertFavoriteHero(heroDM: HeroDM)
    suspend fun deleteFavoriteHero(heroDM: HeroDM): Int

    fun getAllFavoritesHeroes(): Flow<List<HeroDM>>
}