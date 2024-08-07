package com.ndc.moviekuh.ui.screen.dashboard

import com.ndc.moviekuh.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(

) : BaseViewModel<DashboardState, DashboardAction, DashboardEffect>(
    DashboardState()
) {
    override fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.OnBottomSheetVisibilityChange -> updateState {
                copy(
                    homeBottomSheetType = action.type,
                    bottomSheetVisible = action.visible
                )
            }
            is DashboardAction.OnScreenChange ->updateState { copy(currentScreen = action.screen) }
        }
    }
}