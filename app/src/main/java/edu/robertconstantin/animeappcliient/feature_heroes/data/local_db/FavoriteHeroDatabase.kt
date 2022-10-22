package edu.robertconstantin.animeappcliient.feature_heroes.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteHeroEntity::class], version = 1)
abstract class FavoriteHeroDatabase: RoomDatabase() {
    abstract val dao: FavoriteHeroDao
}