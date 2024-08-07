package com.ndc.moviekuh.ui.screen.dashboard

sealed interface DashboardAction {
    data class OnScreenChange(
        val screen: Int
    ) : DashboardAction
    data class OnBottomSheetVisibilityChange(
        val visible: Boolean,
        val type: HomeBottomSheetType = HomeBottomSheetType.NotReady
    ) : DashboardAction
}