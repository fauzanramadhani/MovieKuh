package com.ndc.moviekuh.ui.feature.dashboard

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ndc.moviekuh.R
import com.ndc.moviekuh.ui.component.bottomsheet.NotReadyBottomSheet
import com.ndc.moviekuh.ui.component.textfield.PrimaryTextField
import com.ndc.moviekuh.ui.feature.dashboard.screen.FavoriteScreen
import com.ndc.moviekuh.ui.feature.dashboard.screen.MainScreen
import com.ndc.moviekuh.utils.Toast
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navHostController: NavHostController,
    state: DashboardState,
    effect: DashboardEffect,
    action: (DashboardAction) -> Unit,
) {
    val ctx = LocalContext.current
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val homeBottomNavigationItems = listOf(
        HomeBottomNavigationItem(
            label = "Utama",
            unselectedIcon = R.drawable.ic_home,
            selectedIcon = R.drawable.ic_home_fill,
        ),
        HomeBottomNavigationItem(
            label = "Favorit",
            unselectedIcon = R.drawable.ic_favorite,
            selectedIcon = R.drawable.ic_favorite_fill
        )
    )
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    val mainVerticalScrollState = rememberScrollState()
    val popularListState = rememberLazyListState()
    val nowPlayingListState = rememberLazyListState()
    val topRatedListState = rememberLazyListState()
    val favoriteListState = rememberLazyListState()

    BackHandler {
        (ctx as Activity).finish()
    }

    LaunchedEffect(effect) {
        when (effect) {
            DashboardEffect.Empty -> {}
            is DashboardEffect.OnShowToast -> Toast(ctx, effect.message).short()
        }
    }

    if (state.bottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    bottomSheetState.hide()
                }.invokeOnCompletion {
                    if (!bottomSheetState.isVisible) {
                        action(
                            DashboardAction.OnBottomSheetVisibilityChange(
                                visible = false,
                                type = HomeBottomSheetType.NotReady
                            )
                        )
                    }
                }
            },
            sheetState = bottomSheetState,
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp
            ),
            containerColor = color.background,
            dragHandle = null,
        ) {
            when (val data = state.dashboardBottomSheetType) {
                HomeBottomSheetType.NotReady -> NotReadyBottomSheet()
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color.primary)
            .statusBarsPadding()
            .background(color = color.background)
            .safeDrawingPadding(),
        topBar = {
            when (state.currentScreen) {
                0 -> Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                        .background(color.primary)
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                            bottom = 24.dp
                        ),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrimaryTextField(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "",
                                tint = color.onSurfaceVariant,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        },
                        placeholder = "Cari film",
                        enabled = false,
                        modifier = Modifier
                            .weight(0.8f)
                            .clickable {
                                action(DashboardAction.OnBottomSheetVisibilityChange(visible = true))
                            }
                    )
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_notification
                        ),
                        contentDescription = "",
                        tint = color.onPrimary,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .size(36.dp)
                            .clickable {
                                action(
                                    DashboardAction.OnBottomSheetVisibilityChange(
                                        visible = true,
                                        type = HomeBottomSheetType.NotReady
                                    )
                                )
                            }
                    )
                }

                1 -> Row {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                            .background(color.primary)
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 16.dp,
                                bottom = 24.dp
                            ),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Daftar Film Favorite Saya",
                            style = typography.titleSmall,
                            color = color.onPrimary
                        )
                    }
                }
            }
        },
        bottomBar = {
            Surface(
                shadowElevation = 12.dp,
            ) {
                BottomNavigationBar(
                    homeBottomNavigationItems = homeBottomNavigationItems,
                    selectedIndex = state.currentScreen,
                    onSelectedIndexChange = {
                        action(DashboardAction.OnScreenChange(it))
                    }
                )
            }
        }
    ) { paddingValues ->
        when (state.currentScreen) {
            0 -> MainScreen(
                navHostController = navHostController,
                verticalScrollState = mainVerticalScrollState,
                popularListState = popularListState,
                nowPlayingListState = nowPlayingListState,
                topRatedListState = topRatedListState,
                paddingValues = paddingValues,
                state = state,
                action = action
            )

            1 -> FavoriteScreen(
                navHostController = navHostController,
                listState = favoriteListState,
                paddingValues = paddingValues,
                state = state,
                action = action
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    homeBottomNavigationItems: List<HomeBottomNavigationItem>,
    selectedIndex: Int,
    onSelectedIndexChange: (index: Int) -> Unit
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    BottomAppBar(
        containerColor = Color.Transparent,
    ) {
        homeBottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
            with(bottomNavigationItem) {
                val isSelected = selectedIndex == index
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        onSelectedIndexChange(index)
                    },
                    label = {
                        Text(
                            text = label,
                            style = typography.labelSmall
                        )
                    },
                    icon = {
                        Icon(
                            painterResource(
                                id = if (isSelected) selectedIcon
                                else unselectedIcon
                            ),
                            contentDescription = ""
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = color.primary,
                        unselectedIconColor = color.secondary,
                        selectedTextColor = color.primary,
                        unselectedTextColor = color.secondary,
                        indicatorColor = color.primaryContainer
                    )
                )
            }
        }
    }
}