package com.jobsity.sitytv.core.di.use_cases

import androidx.paging.PagingSource
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.core.domain.use_cases.GetShowUseCase
import com.jobsity.sitytv.core.domain.use_cases.GetShowUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object GetShowsUseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesGetShowsUseCase(showPagingSource: PagingSource<Int, ShowItem>): GetShowUseCase {
        return GetShowUseCaseImpl(showPagingSource)
    }
}
