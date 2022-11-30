package com.jobsity.sitytv.core.di

import com.jobsity.sitytv.core.data.external.data_source.RemoteDataSource
import com.jobsity.sitytv.core.data.external.repository.RemoteRepository
import com.jobsity.sitytv.core.data.external.repository.TvMazeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteRepositoryModule {
    @Provides
    @Singleton
    fun providesRemoteRepository(remoteDataSource: RemoteDataSource): RemoteRepository {
        return TvMazeRepository(remoteDataSource)
    }
}
