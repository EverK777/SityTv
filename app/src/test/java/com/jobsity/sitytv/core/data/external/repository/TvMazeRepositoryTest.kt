package com.jobsity.sitytv.core.data.external.repository

import com.jobsity.sitytv.core.data.external.data_source.RemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvMazeRepositoryTest {

    private lateinit var sut: TvMazeRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun serUp() {
        sut = TvMazeRepository(remoteDataSource = remoteDataSource)
    }

    @Test
    fun whenRequestShowThenDataSourceIsCalled(): Unit = runBlocking {
        sut.requestShows(1)
        Mockito.verify(remoteDataSource, Mockito.times(1)).requestShows(1)
    }
}
