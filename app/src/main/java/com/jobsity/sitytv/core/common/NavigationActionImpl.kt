package com.jobsity.sitytv.core.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

class NavigationActionImpl : NavigationAction, Fragment() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
    }

    override fun navigateToNextFragment(fragmentRes: Int) {
        if (verifyNavControllerInitialized()) {
            navController.navigate(fragmentRes)
        }
    }

    override fun navigateToNextFragment(action: NavDirections) {
        if (verifyNavControllerInitialized()) {
            navController.navigate(action)
        }
    }

    private fun verifyNavControllerInitialized(): Boolean {
        return ::navController.isInitialized
    }
}
