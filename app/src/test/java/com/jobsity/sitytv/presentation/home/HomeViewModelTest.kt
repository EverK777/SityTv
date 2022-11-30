package com.jobsity.sitytv.presentation.home

import androidx.paging.PagingData
import com.google.common.truth.Truth
import com.jobsity.sitytv.core.common.AppConstants
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.core.domain.use_cases.GetShowUseCase
import com.jobsity.sitytv.presentation.features.home.HomeViewModel
import com.jobsity.sitytv.utils.MainCoroutineRule
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    private lateinit var showUseCase: GetShowUseCase

    private lateinit var sut: HomeViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val pagingData = PagingData.from(
        listOf(
            ShowItem(
                1,
                null,
                emptyList(),
                null,
                "Test name",
                null,
                "Test",
                false
            )
        )
    )

    @Before
    fun setUp() {
        Mockito.`when`(showUseCase.getShowsPaged(AppConstants.PAGE_SIZE, AppConstants.JUMP_THRESHOLD))
            .thenReturn(
                flowOf(pagingData)
            )
        sut = HomeViewModel(showUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testFlowData() = runTest {
        Truth.assertThat(sut.getShows.first()).isInstanceOf(PagingData::class.java)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCurrentIndexDefaultValue() = runTest {
        Truth.assertThat(sut.currentShowIndex.value).isEqualTo(0)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCurrentIndexChanged() = runTest {
        sut.setCurrentShowIndexIndex(4)
        Truth.assertThat(sut.currentShowIndex.value).isEqualTo(4)
    }
}
