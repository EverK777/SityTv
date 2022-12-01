package com.jobsity.sitytv.core.helpers

import com.jobsity.sitytv.core.domain.models.SeasonsItem

fun SeasonsItem.getListOfEpisodes(spanText: String): List<String> {
    val episodes: ArrayList<String> = ArrayList()
    val totalEpisodes = this.episodeOrder ?: 0
    for (i in 0 until totalEpisodes) {
        episodes.add("$spanText ${i + 1}")
    }
    return episodes
}
