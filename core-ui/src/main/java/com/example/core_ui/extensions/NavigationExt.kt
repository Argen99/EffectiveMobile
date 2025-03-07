package com.example.core_ui.extensions

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigation(
    @IdRes actionId: Int,
) {
    currentDestination?.getAction(actionId)?.let { navigate(actionId) }
}

fun NavController.safeNavigation(
    directions: NavDirections
) {
    currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
}