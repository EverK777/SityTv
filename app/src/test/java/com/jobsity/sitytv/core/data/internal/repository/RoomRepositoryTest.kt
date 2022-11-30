package com.jobsity.sitytv.core.data.internal.repository

import com.google.common.truth.Truth
import com.jobsity.sitytv.core.data.internal.data_source.FavoriteDao
import com.jobsity.sitytv.core.domain.models.FavoriteEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoomRepositoryTest {

    @Mock
    lateinit var dao: FavoriteDao

    private lateinit var roomRepositoryImpl: LocalRepository

    @Before
    fun setUp() {
        roomRepositoryImpl = RoomRepositoryImpl(dao)
    }

    @Test
    fun whenSearchAFavoriteReturnsFalse(): Unit = runBlocking {
        Mockito.`when`(dao.getFavoritesShows(1))
            .thenReturn(null)

        val result = roomRepositoryImpl.searchFavorite(1)
        Truth.assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenSearchAFavoriteReturnsTrue(): Unit = runBlocking {
        Mockito.`when`(dao.getFavoritesShows(1))
            .thenReturn(
                FavoriteEntity(
                    1,
                    "test",
                    "test",
                    "test",
                    "test",
                    "test"
                )
            )

        val result = roomRepositoryImpl.searchFavorite(1)
        Truth.assertThat(result).isEqualTo(true)
    }
}
