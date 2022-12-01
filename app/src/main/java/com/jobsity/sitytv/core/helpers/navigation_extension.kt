package com.jobsity.sitytv.core.helpers

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Fragment.navigateToNextFragment(action: NavDirections) {
    Navigation.findNavController(this.requireView()).navigate(action)
}
