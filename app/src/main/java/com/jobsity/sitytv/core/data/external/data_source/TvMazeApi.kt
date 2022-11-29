package com.jobsity.sitytv.core.data.external.data_source

import com.jobsity.sitytv.core.domain.models.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {
    @GET("shows")
    suspend fun requestCurrentSeriesList(@Query("page") page: Int): ShowResponse
}
