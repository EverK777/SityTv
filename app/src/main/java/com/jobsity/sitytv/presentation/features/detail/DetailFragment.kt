package com.jobsity.sitytv.presentation.features.detail

import android.os.Bundle
import android.view.View
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.layout.ContentScale
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.common.*
import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.helpers.RequestStateApi
import com.jobsity.sitytv.core.helpers.initComposeView
import com.jobsity.sitytv.core.helpers.navigateToNextFragment
import com.jobsity.sitytv.databinding.FragmentDetailBinding
import com.jobsity.sitytv.presentation.composables.BannerShowComposable
import com.jobsity.sitytv.presentation.composables.expandable_view.ExpandableViewComposable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@AndroidEntryPoint
class DetailFragment :
    FragmentBindingCreator<FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.seasonsFlow(args.show.id).collect {
                when (it) {
                    is RequestStateApi.Loading -> binding.progressBarEpisodes.visibility = View.VISIBLE
                    is RequestStateApi.Success -> configComposableExpanded(it.value)
                    is RequestStateApi.Error -> binding.progressBarEpisodes.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setBindingProperties()
        initComposeView({
            BannerShowComposable(args.show.image?.original ?: "") { ContentScale.Crop }
        }, binding.bannerComposable)
        binding.seasonsContainer.setOnClickListener { showSeasons() }
        binding.closeFavoritesButton.setOnClickListener { closeSeasons() }
        binding.backIcon.setOnClickListener { handleBackPress() }
    }

    private fun configComposableExpanded(seasons: SeasonsResponse) {
        binding.progressBarEpisodes.visibility = View.GONE
        initComposeView({
            ExpandableViewComposable(viewModel = viewModel, seasons = seasons) { season, episode ->
                navigateToNextFragment(
                    DetailFragmentDirections.actionDetailFragmentToEpisodeDetail(
                        args.show.id,
                        season,
                        episode
                    )
                )
            }
        }, binding.expandableListComposable)
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun closeSeasons() {
        binding.seasonsContainerContent.visibility = View.GONE
    }

    private fun showSeasons() {
        binding.seasonsContainerContent.visibility = View.VISIBLE
    }

    private fun setBindingProperties() {
        binding.name = args.show.name
        binding.genres = args.show.genres.joinToString { it }
        binding.schedule = getSchedule()
        binding.summaryView.text = HtmlCompat.fromHtml(args.show.summary ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun getSchedule(): String {
        val days = args.show.schedule?.days?.joinToString { it }
        val time = args.show.schedule?.time
        val duration = args.show.runtime
        val durationValidation = if (duration == null) "" else "($duration)"
        return "$days at $time $durationValidation"
    }
}
