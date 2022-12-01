package com.jobsity.sitytv.presentation.features.episode

import androidx.lifecycle.ViewModel
import com.jobsity.sitytv.core.domain.models.EpisodeDetailResponse
import com.jobsity.sitytv.core.domain.use_cases.GetEpisodeDetailUseCase
import com.jobsity.sitytv.core.helpers.RequestStateApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase
) : ViewModel() {

    fun episodeDetailFlow(showId: Int, season: Int, episodeNumber: Int): Flow<RequestStateApi<EpisodeDetailResponse>> {
        return getEpisodeDetailUseCase.getEpisodeDetail(showId = showId, season = season, episodeNumber = episodeNumber)
    }
}
