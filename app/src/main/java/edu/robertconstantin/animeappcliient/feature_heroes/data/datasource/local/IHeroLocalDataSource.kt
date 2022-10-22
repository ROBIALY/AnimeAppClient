package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local

import edu.robertconstantin.animeappcliient.feature_heroes.data.local_db.FavoriteHeroEntity

interface IHeroLocalDataSource {
    suspend fun insertFavoriteHero(favoriteHero: FavoriteHeroEntity)
    suspend fun deleteFavoriteHero(favoriteHero: FavoriteHeroEntity): Int
}