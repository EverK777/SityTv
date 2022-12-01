package com.jobsity.sitytv.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeDetailResponse(
    val id: Int?,
    val name: String?,
    val number: Int?,
    val season: Int?,
    val summary: String?,
    val image: Image?
) : Parcelable
