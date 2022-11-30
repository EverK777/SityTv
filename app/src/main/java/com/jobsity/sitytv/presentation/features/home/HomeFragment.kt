package com.jobsity.sitytv.presentation.features.home

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.fragment.app.viewModels
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.common.*
import com.jobsity.sitytv.databinding.FragmentHomeBinding
import com.jobsity.sitytv.presentation.composables.LogoAppComposable
import com.jobsity.sitytv.presentation.features.home.composable.ShowsComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPagerApi
@ExperimentalComposeUiApi
class HomeFragment :
    FragmentBindingCreator<FragmentHomeBinding>(),
    ComposeViewConfig by ComposeViewImpl(),
    NavigationAction by NavigationActionImpl() {

    private val viewModel: HomeViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onStart() {
        super.onStart()
        initComposeView({
            LogoAppComposable()
        }, binding.logoAppComposable)

        initComposeView({
            ShowsComposable(homeViewModelEvents = viewModel)
        }, binding.listOfShowsComposable)
    }
}
