package edu.robertconstantin.animeappcliient.feature_heroes.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteHeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteHero(favoriteHero: FavoriteHeroEntity)

    @Delete
    suspend fun deleteFavoriteHero(favoriteHero: FavoriteHeroEntity): Int

    @Query("SELECT * FROM favorites_hero_db")
    fun getAllFavoritesHeroes(): Flow<List<FavoriteHeroEntity>>
}