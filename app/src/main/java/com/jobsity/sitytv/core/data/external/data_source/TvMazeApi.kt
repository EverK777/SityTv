package com.jobsity.sitytv.core.data.external.data_source

import com.jobsity.sitytv.core.domain.models.EpisodeDetailResponse
import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.domain.models.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApi {
    @GET("shows")
    suspend fun requestCurrentSeriesList(@Query("page") page: Int): ShowResponse

    @GET("shows/{id}/seasons")
    suspend fun requestSeasons(@Path("id") id: Int): SeasonsResponse

    @GET("shows/{id}/episodebynumber")
    suspend fun getEpisodeDetail(@Path("id") id: Int, @Query("season") season: Int, @Query("number") number: Int): EpisodeDetailResponse

    @GET("search/shows")
    suspend fun searchShow(@Query("q") query: String): SearchResultResponse
}
