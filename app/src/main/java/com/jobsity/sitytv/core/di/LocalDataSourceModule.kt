package com.jobsity.sitytv.core.di

import android.content.Context
import androidx.room.Room
import com.jobsity.sitytv.core.data.internal.data_source.FavoriteDao
import com.jobsity.sitytv.core.data.internal.data_source.SityTvDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    @Singleton
    fun providesROOMDB(@ApplicationContext context: Context): SityTvDB {
        return Room.databaseBuilder(
            context,
            SityTvDB::class.java,
            "sity_tv_db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun providesFavoriteDao(sityTvDB: SityTvDB): FavoriteDao {
        return sityTvDB.favoritesDao()
    }
}
