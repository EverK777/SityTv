package com.jobsity.sitytv.core.di.use_cases

import com.jobsity.sitytv.core.data.external.repository.RemoteRepository
import com.jobsity.sitytv.core.domain.use_cases.SearchShowsUseCase
import com.jobsity.sitytv.core.domain.use_cases.SearchShowsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SearchUseCaseModule {
    @Provides
    @ViewModelScoped
    fun providesSearchUseCase(remoteRepository: RemoteRepository): SearchShowsUseCase {
        return SearchShowsUseCaseImpl(remoteRepository)
    }
}
