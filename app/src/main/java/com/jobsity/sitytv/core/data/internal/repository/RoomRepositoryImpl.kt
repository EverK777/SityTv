package com.jobsity.sitytv.core.data.internal.repository

import com.jobsity.sitytv.core.data.internal.data_source.FavoriteDao

class RoomRepositoryImpl constructor(
    private val favoriteDao: FavoriteDao
) : LocalRepository {
    override suspend fun searchFavorite(id: Int): Boolean {
        return favoriteDao.getFavoritesShows(id) != null
    }
}
