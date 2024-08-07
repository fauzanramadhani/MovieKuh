package com.ndc.moviekuh.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ndc.moviekuh.ui.screen.dashboard.DashboardEffect
import com.ndc.moviekuh.ui.screen.dashboard.DashboardScreen
import com.ndc.moviekuh.ui.screen.dashboard.DashboardViewModel

@Composable
fun SetupNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoute.DashboardScreen.route,
        route = NavRoute.MainRoute.route,
    ) {
        composable(
            route = NavRoute.DashboardScreen.route
        ) {
            val viewModel = hiltViewModel<DashboardViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val effect by viewModel.onEffect.collectAsStateWithLifecycle(initialValue = DashboardEffect.Empty)

            DashboardScreen(
                navHostController = navHostController,
                state = state,
                effect = effect,
                action = viewModel::onAction
            )
        }
    }
}