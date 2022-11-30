package com.jobsity.sitytv.core.di

import androidx.paging.PagingSource
import com.jobsity.sitytv.core.data.external.repository.RemoteRepository
import com.jobsity.sitytv.core.data.internal.repository.LocalRepository
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.core.helpers.ShowPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShowPagingSourceModule {

    @Provides
    @Singleton
    fun providesShowsPagingSource(
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository
    ): PagingSource<Int, ShowItem> {
        return ShowPagingSource(localRepository, remoteRepository)
    }
}
