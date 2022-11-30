package com.jobsity.sitytv.core.helpers

import androidx.paging.PagingSource
import com.google.common.truth.Truth
import com.jobsity.sitytv.core.data.external.repository.RemoteRepository
import com.jobsity.sitytv.core.data.internal.repository.LocalRepository
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.core.domain.models.ShowResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowPagingSourceTest {

    private lateinit var sut: ShowPagingSource

    @Mock
    private lateinit var localRepository: LocalRepository

    @Mock
    private lateinit var remoteRepository: RemoteRepository

    @Before
    fun setUp() {
        sut = ShowPagingSource(localRepository = localRepository, remoteRepository = remoteRepository)
    }

    @Test
    fun whenLoadNewPageThenReturnsLoadResultPage(): Unit = runBlocking {
        val fakeShows = getFakeShow()

        Mockito.`when`(localRepository.searchFavorite(1))
            .thenReturn(true)

        for (i in 0..30) {
            if (i != 1) {
                Mockito.`when`(localRepository.searchFavorite(i))
                    .thenReturn(false)
            }
        }

        Mockito.`when`(remoteRepository.requestShows(1))
            .thenReturn(ApiResultHandle.Success(fakeShows))

        val result = sut.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        Truth.assertThat(result).isEqualTo(
            PagingSource.LoadResult.Page(
                data = fakeShows,
                prevKey = null,
                nextKey = 2
            )
        )
    }

    @Test
    fun whenLoadNewPageThenReturnsEmptyItems(): Unit = runBlocking {
        getFakeShow()
        val result = sut.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )
        Truth.assertThat(result.javaClass).isEqualTo(PagingSource.LoadResult.Error::class.java)
    }

    @Test
    fun whenLoadNewPageThenReturnsApiError(): Unit = runBlocking {
        Mockito.`when`(remoteRepository.requestShows(1))
            .thenReturn(ApiResultHandle.ApiError("500", "Error"))
        val result = sut.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )
        Truth.assertThat(result.javaClass).isEqualTo(PagingSource.LoadResult.Error::class.java)
    }

    @Test
    fun whenLoadNewPageThenReturnsNetworkError(): Unit = runBlocking {
        Mockito.`when`(remoteRepository.requestShows(1))
            .thenReturn(ApiResultHandle.NetworkError("error"))
        val result = sut.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )
        Truth.assertThat(result.javaClass).isEqualTo(PagingSource.LoadResult.Error::class.java)
    }

    private fun getFakeShow(): ShowResponse {
        val fakeListTmp = ShowResponse()
        for (i in 0..30) {
            fakeListTmp.add(
                ShowItem(
                    i,
                    null,
                    emptyList(),
                    null,
                    "",
                    null,
                    "",
                    i == 1

                )
            )
        }
        return fakeListTmp
    }
}
