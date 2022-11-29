package com.jobsity.sitytv.presentation.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jobsity.sitytv.R
import com.jobsity.sitytv.core.common.*
import com.jobsity.sitytv.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    FragmentBindingCreator<FragmentHomeBinding>(),
    ComposeViewConfig by ComposeViewImpl(),
    NavigationAction by NavigationActionImpl() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    override fun onStart() {
        super.onStart()
        initComposeView({
            Greeting(name = "Ever")
        }, binding.mainComposeView)
    }
}
