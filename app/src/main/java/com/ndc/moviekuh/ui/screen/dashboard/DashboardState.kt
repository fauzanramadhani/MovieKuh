package com.ndc.moviekuh.ui.screen.dashboard

data class DashboardState(
    val currentScreen: Int = 0,

    val bottomSheetVisible: Boolean = false,
    val homeBottomSheetType: HomeBottomSheetType = HomeBottomSheetType.NotReady,
)
