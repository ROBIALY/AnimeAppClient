package edu.robertconstantin.animeappcliient.feature_heroes.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface FavoriteHeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteHero(favoriteHero: FavoriteHeroEntity)

    @Delete
    suspend fun deleteFavoriteHero(favoriteHero: FavoriteHeroEntity): Int
}