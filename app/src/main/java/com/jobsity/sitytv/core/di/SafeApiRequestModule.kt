package com.jobsity.sitytv.core.di

import android.app.Application
import com.jobsity.sitytv.core.helpers.SafeApiRequest
import com.jobsity.sitytv.core.helpers.SafeApiRequestImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SafeApiRequestModule {
    @Singleton
    @Provides
    fun providesSafeApiRequestUtil(application: Application): SafeApiRequest {
        return SafeApiRequestImpl(application)
    }
}
