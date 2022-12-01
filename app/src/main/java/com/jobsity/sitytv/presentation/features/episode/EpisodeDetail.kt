package com.jobsity.sitytv.presentation.features.episode

import android.os.Bundle
import android.view.View
import androidx.compose.ui.layout.ContentScale
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.common.FragmentBindingCreator
import com.jobsity.sitytv.core.domain.models.EpisodeDetailResponse
import com.jobsity.sitytv.core.helpers.RequestStateApi
import com.jobsity.sitytv.core.helpers.initComposeView
import com.jobsity.sitytv.databinding.FragmentEpisodeDetailBinding
import com.jobsity.sitytv.presentation.composables.BannerShowComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetail : FragmentBindingCreator<FragmentEpisodeDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_episode_detail

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private val args: EpisodeDetailArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.episodeDetailFlow(showId = args.showId, season = args.seasonNumber, episodeNumber = args.episodeNumber).collect {
                when (it) {
                    is RequestStateApi.Loading -> binding.progressView.visibility = View.VISIBLE
                    is RequestStateApi.Success -> configView(it.value)
                    is RequestStateApi.Error -> binding.progressView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun configView(value: EpisodeDetailResponse) {
        binding.progressView.visibility = View.GONE
        binding.episodeName = formatEpisodeName(value)
        binding.numberSeason = value.season?.toString()
        binding.summaryView.text = HtmlCompat.fromHtml(value.summary ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
        initComposeView({
            BannerShowComposable(value.image?.original ?: "") { ContentScale.Crop }
        }, binding.bannerComposable)

        binding.backIcon.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
    }

    private fun formatEpisodeName(value: EpisodeDetailResponse): String {
        return String.format(getString(R.string.episode_number), args.episodeNumber) + " - " + value.name
    }
}
