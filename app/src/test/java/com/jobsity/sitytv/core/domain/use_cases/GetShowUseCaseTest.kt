package com.jobsity.sitytv.core.domain.use_cases

import androidx.paging.PagingSource
import com.jobsity.sitytv.core.domain.models.ShowItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetShowUseCaseTest {
    @Mock
    private lateinit var pagingSource: PagingSource<Int, ShowItem>

    private lateinit var sut: GetShowUseCaseImpl

    @Before
    fun setUp() {
        sut = GetShowUseCaseImpl(pagingSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun verifiesJumpSupportedTrue() = runTest {
        Mockito.`when`(pagingSource.jumpingSupported).thenReturn(true)
        sut.getShowsPaged(20, 15).first()
        Mockito.verify(pagingSource, Mockito.times(1)).jumpingSupported
    }
}
