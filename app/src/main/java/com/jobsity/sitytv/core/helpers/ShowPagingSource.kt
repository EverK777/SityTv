package com.jobsity.sitytv.core.helpers

import android.accounts.NetworkErrorException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jobsity.sitytv.core.data.external.repository.RemoteRepository
import com.jobsity.sitytv.core.data.internal.repository.LocalRepository
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.core.domain.models.ShowResponse
import com.jobsity.sitytv.core.exceptions.ApiErrorException

class ShowPagingSource constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : PagingSource<Int, ShowItem>() {

    override val jumpingSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, ShowItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowItem> {
        return try {
            createPagination(params)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun createPagination(params: LoadParams<Int>): LoadResult<Int, ShowItem> {
        val nextPage = params.key ?: 1
        return when (val listReturnedFromApi = remoteRepository.requestShows(nextPage)) {
            is ApiResultHandle.ApiError -> {
                LoadResult.Error(ApiErrorException())
            }
            is ApiResultHandle.NetworkError -> {
                LoadResult.Error(NetworkErrorException(listReturnedFromApi.errorNetworkMessage))
            }
            is ApiResultHandle.Success -> {
                val data = getData(listReturnedFromApi)
                validateData(data, nextPage)
            }
        }
    }

    private fun validateData(data: List<ShowItem>, nextPage: Int): LoadResult<Int, ShowItem> {
        return try {
            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getData(listReturnedFromApi: ApiResultHandle.Success<ShowResponse>): List<ShowItem> {
        listReturnedFromApi.value.let { showsResponse ->
            return showsResponse.map {
                ShowItem(
                    id = it.id,
                    links = it.links,
                    genres = it.genres,
                    image = it.image,
                    name = it.name,
                    schedule = it.schedule,
                    summary = it.summary,
                    favorite = checkIfIsFavorite(it.id)
                )
            }
        }
    }

    private suspend fun checkIfIsFavorite(id: Int): Boolean {
        return localRepository.searchFavorite(id)
    }
}
