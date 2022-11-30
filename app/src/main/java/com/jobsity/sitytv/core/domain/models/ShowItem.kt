package com.jobsity.sitytv.core.domain.models

import com.google.gson.annotations.SerializedName

data class ShowItem(
    val id: Int,
    @SerializedName("_links")
    val links: Links?,
    val genres: List<String>,
    val image: Image?,
    val name: String?,
    val schedule: Schedule?,
    val summary: String?,
    val favorite: Boolean?
)
