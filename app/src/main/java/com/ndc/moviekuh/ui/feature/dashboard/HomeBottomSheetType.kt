package com.ndc.moviekuh.ui.feature.dashboard

sealed interface HomeBottomSheetType {
    data object NotReady : HomeBottomSheetType
}