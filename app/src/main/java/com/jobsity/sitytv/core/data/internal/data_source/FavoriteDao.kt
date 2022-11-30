package com.jobsity.sitytv.core.data.internal.data_source

import androidx.room.*
import com.jobsity.sitytv.core.domain.models.FavoriteEntity

@Dao
interface FavoriteDao {
    @Transaction
    @Query("SELECT * FROM favoriteentity where id = :id")
    suspend fun getFavoritesShows(id: Int): FavoriteEntity?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)
}
