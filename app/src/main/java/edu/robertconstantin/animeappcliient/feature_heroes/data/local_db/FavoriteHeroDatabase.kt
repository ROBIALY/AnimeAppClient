package edu.robertconstantin.animeappcliient.feature_heroes.data.local_db

import androidx.room.Database

@Database(entities = [FavoriteHeroEntity::class], version = 1)
abstract class FavoriteHeroDatabase {
    abstract val dao: FavoriteHeroDao
}