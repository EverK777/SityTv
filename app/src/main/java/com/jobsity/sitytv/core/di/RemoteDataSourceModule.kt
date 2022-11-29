package com.jobsity.sitytv.core.di

import com.google.gson.GsonBuilder
import com.jobsity.sitytv.BuildConfig
import com.jobsity.sitytv.core.data.external.data_source.TvMazeApi
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providesRetrofitDataSource(): TvMazeApi {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }
        return Retrofit.Builder()
            .client(builder.build())
            .baseUrl(BuildConfig.TV_MAZE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(TvMazeApi::class.java)
    }
}
