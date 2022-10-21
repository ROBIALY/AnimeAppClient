package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.local

import edu.robertconstantin.animeappcliient.feature_heroes.data.local_db.FavoriteHeroDao
import edu.robertconstantin.animeappcliient.feature_heroes.data.local_db.FavoriteHeroEntity

class HeroLocalDataSource(private val dao: FavoriteHeroDao): IHeroLocalDataSource {
    override suspend fun insertFavoriteHero(favoriteHero: FavoriteHeroEntity) {
        dao.insertFavoriteHero(favoriteHero)
    }
}