package com.ndc.moviekuh.ui.feature.dashboard

sealed interface DashboardEffect {
    data object Empty : DashboardEffect
    data class OnShowToast(
        val message: String
    ) : DashboardEffect
}