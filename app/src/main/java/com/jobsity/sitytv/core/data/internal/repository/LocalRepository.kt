package com.jobsity.sitytv.core.data.internal.repository

interface LocalRepository {
    suspend fun searchFavorite(id: Int): Boolean
}
