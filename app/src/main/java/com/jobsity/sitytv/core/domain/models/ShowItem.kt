package com.jobsity.sitytv.core.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowItem(
    val id: Int,
    @SerializedName("_links")
    val links: Links?,
    val genres: List<String>,
    val image: Image?,
    val name: String?,
    val schedule: Schedule?,
    val runtime: Int?,
    val summary: String?,
    val favorite: Boolean?
) : Parcelable
