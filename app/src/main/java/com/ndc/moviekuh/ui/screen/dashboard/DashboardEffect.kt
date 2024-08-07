package com.ndc.moviekuh.ui.screen.dashboard

sealed interface DashboardEffect {
    data object Empty : DashboardEffect
    data class OnError(
        val message: String
    ) : DashboardEffect
}