package com.jobsity.sitytv.core.common

import androidx.navigation.NavDirections

interface NavigationAction {
    fun navigateToNextFragment(fragmentRes: Int)
    fun navigateToNextFragment(action: NavDirections)
}
