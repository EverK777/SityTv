package com.jobsity.sitytv.core.di

import com.jobsity.sitytv.core.data.external.data_source.RemoteDataSource
import com.jobsity.sitytv.core.data.external.data_source.TvMazeApi
import com.jobsity.sitytv.core.data.external.data_source.TvMazeDataSource
import com.jobsity.sitytv.core.helpers.SafeApiRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun providesRemoteDataSource(
        tvMazeApi: TvMazeApi,
        safeApiRequest: SafeApiRequest,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): RemoteDataSource {
        return TvMazeDataSource(tvMazeApi, safeApiRequest, coroutineDispatcher)
    }
}
