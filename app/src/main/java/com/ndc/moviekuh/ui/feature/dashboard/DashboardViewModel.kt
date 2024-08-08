package com.ndc.moviekuh.ui.feature.dashboard

import androidx.lifecycle.viewModelScope
import com.ndc.moviekuh.base.BaseViewModel
import com.ndc.moviekuh.data.source.network.response.NowPlayingMovieItem
import com.ndc.moviekuh.data.source.network.response.PopularMovieItem
import com.ndc.moviekuh.data.source.network.response.TopRatedMovieItem
import com.ndc.moviekuh.domain.GetNowPlayingMovieListUseCase
import com.ndc.moviekuh.domain.GetPopularMovieListUseCase
import com.ndc.moviekuh.domain.GetTopRatedMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getNowPlayingMovieListUseCase: GetNowPlayingMovieListUseCase,
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase,
) : BaseViewModel<DashboardState, DashboardAction, DashboardEffect>(
    DashboardState()
) {
    init {
        getPopularMovieList()
        getNowPlayingMovieList()
        getTopRatedMovieList()
    }

    override fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.OnBottomSheetVisibilityChange -> updateState {
                copy(
                    dashboardBottomSheetType = action.type,
                    bottomSheetVisible = action.visible
                )
            }

            is DashboardAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
        }
    }

    private fun getPopularMovieList() = viewModelScope.launch {
        getPopularMovieListUseCase
            .invoke(
                limit = 5
            )
            .retry { cause ->
                when (cause) {
                    is IOException -> true
                    is HttpException -> true
                    else -> false
                }
            }
            .catch {
                onShowToast(it.message.toString())
            }
            .onEach {
                updateState {
                    copy(
                        popularMovieList = it as List<PopularMovieItem>,
                        popularMovieLoading = false
                    )
                }
            }
            .collect()
    }

    private fun getNowPlayingMovieList() = viewModelScope.launch {
        getNowPlayingMovieListUseCase
            .invoke(
                limit = 5
            )
            .retry { cause ->
                when (cause) {
                    is IOException -> true
                    is HttpException -> true
                    else -> false
                }
            }
            .catch {
                onShowToast(it.message.toString())
            }
            .onEach {
                updateState {
                    copy(
                        nowPlayingMovieList = it as List<NowPlayingMovieItem>,
                        nowPlayingMovieLoading = false
                    )
                }
            }
            .collect()
    }

    private fun getTopRatedMovieList() = viewModelScope.launch {
        getTopRatedMovieListUseCase
            .invoke(
                limit = 5
            )
            .retry { cause ->
                when (cause) {
                    is IOException -> true
                    is HttpException -> true
                    else -> false
                }
            }
            .catch {
                onShowToast(it.message.toString())
            }
            .onEach {
                updateState {
                    copy(
                        topRatedMovieList = it as List<TopRatedMovieItem>,
                        topRatedMovieLoading = false
                    )
                }
            }
            .collect()
    }

    private fun onShowToast(message: String) = viewModelScope.launch {
        sendEffect(DashboardEffect.OnShowToast(message))
        delay(1000)
        sendEffect(DashboardEffect.Empty)
    }
}