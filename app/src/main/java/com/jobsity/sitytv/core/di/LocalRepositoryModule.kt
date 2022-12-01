package com.jobsity.sitytv.core.di

import com.jobsity.sitytv.core.data.internal.data_source.FavoriteDao
import com.jobsity.sitytv.core.data.internal.repository.LocalRepository
import com.jobsity.sitytv.core.data.internal.repository.RoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalRepositoryModule {
    @Provides
    @Singleton
    fun providesLocalRepository(favoriteDao: FavoriteDao): LocalRepository {
        return RoomRepositoryImpl(favoriteDao)
    }
}
