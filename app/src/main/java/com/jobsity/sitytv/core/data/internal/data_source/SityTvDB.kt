package com.jobsity.sitytv.core.data.internal.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jobsity.sitytv.core.domain.models.FavoriteEntity

@Database(
    entities = [
        FavoriteEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class SityTvDB : RoomDatabase() {
    abstract fun favoritesDao(): FavoriteDao
}
