package com.jobsity.sitytv.presentation.features.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.common.*
import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.domain.models.ShowItem
import com.jobsity.sitytv.core.helpers.RequestStateApi
import com.jobsity.sitytv.core.helpers.initComposeView
import com.jobsity.sitytv.core.helpers.navigateToNextFragment
import com.jobsity.sitytv.databinding.FragmentHomeBinding
import com.jobsity.sitytv.presentation.composables.LogoAppComposable
import com.jobsity.sitytv.presentation.composables.SearchShowsList
import com.jobsity.sitytv.presentation.features.home.composable.ShowsComposable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class HomeFragment :
    FragmentBindingCreator<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComposeView({
            LogoAppComposable()
        }, binding.logoAppComposable)

        initComposeView({
            ShowsComposable(homeViewModelEvents = viewModel) { navigateToDetail(it) }
        }, binding.listOfShowsComposable)

        binding.searchView.setOnClickListener {
            binding.searchViewContainer.visibility = View.VISIBLE
        }
        binding.searchIconImage.setOnClickListener { requestShowsByText() }
    }

    private fun requestShowsByText() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onSearch(binding.editTextSearch.text.toString()).collect {
                when (it) {
                    is RequestStateApi.Loading -> binding.progressSearch.visibility = View.VISIBLE
                    is RequestStateApi.Success -> initComposeViewSearch(it.value)
                    is RequestStateApi.Error -> binding.progressSearch.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initComposeViewSearch(value: SearchResultResponse) {
        binding.progressSearch.visibility = View.GONE
        initComposeView({
            SearchShowsList(value) {
                navigateToDetail(it)
            }
        }, binding.searchResult)
    }

    private fun navigateToDetail(itemShow: ShowItem) {
        navigateToNextFragment(HomeFragmentDirections.actionHomeFragmentToDetailFragment(itemShow))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                if (binding.searchViewContainer.visibility == View.VISIBLE) {
                    binding.searchViewContainer.visibility = View.GONE
                } else {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }
}
