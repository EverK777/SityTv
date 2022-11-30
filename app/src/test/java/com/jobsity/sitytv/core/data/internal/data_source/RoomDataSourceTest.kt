package com.jobsity.sitytv.core.data.internal.data_source

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.jobsity.sitytv.core.domain.models.FavoriteEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@Config(sdk = [21, 30])
@RunWith(RobolectricTestRunner::class)
class RoomDataSourceTest {

    private lateinit var favoriteDao: FavoriteDao
    private lateinit var db: SityTvDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SityTvDB::class.java)
            .build()
        favoriteDao = db.favoritesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun whenRequestAFavoriteShowReturnNull(): Unit = runBlocking {
        val isFavorite = favoriteDao.getFavoritesShows(1)
        Truth.assertThat(isFavorite == null).isTrue()
    }

    @Test
    @Throws(Exception::class)
    fun whenRequestAFavoriteShowReturnAFavorite(): Unit = runBlocking {
        val favoriteEntity = FavoriteEntity(
            1,
            "test",
            "test",
            "test",
            "test",
            "test"
        )
        favoriteDao.insertFavorite(favoriteEntity)
        val result = favoriteDao.getFavoritesShows(1)
        Truth.assertThat(result).isEqualTo(favoriteEntity)
    }
}
