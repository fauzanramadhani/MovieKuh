package com.ndc.moviekuh.ui.feature.detailnowplayingmovie

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ndc.moviekuh.base.BaseViewModel
import com.ndc.moviekuh.data.source.network.response.NowPlayingMovieItem
import com.ndc.moviekuh.domain.GetNowPlayingMoviePagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNowPlayingMovieViewModel @Inject constructor(
    private val getNowPlayingMoviePagingUseCase: GetNowPlayingMoviePagingUseCase
) : BaseViewModel<DetailNowPlayingMovieState, DetailNowPlayingMovieAction, DetailNowPlayingMovieEffect>(
    DetailNowPlayingMovieState()
) {
    private val _nowPlayingMoviePagingState: MutableStateFlow<PagingData<NowPlayingMovieItem>> =
        MutableStateFlow(value = PagingData.empty())
    val nowPlayingMoviePagingState: StateFlow<PagingData<NowPlayingMovieItem>> get() = _nowPlayingMoviePagingState

    init {
        getNowPlayingMoviePaging()
    }

    private fun getNowPlayingMoviePaging() = viewModelScope.launch {
        getNowPlayingMoviePagingUseCase
            .invoke()
            .distinctUntilChanged()
            .catch {
                onShowToast(it.message.toString())
            }
            .cachedIn(this)
            .onEach {
                _nowPlayingMoviePagingState.value = it
            }
            .collect()
    }

    private fun onShowToast(message: String) = viewModelScope.launch {
        sendEffect(DetailNowPlayingMovieEffect.OnShowToast(message))
        delay(1000)
        sendEffect(DetailNowPlayingMovieEffect.Empty)
    }

    override fun onAction(action: DetailNowPlayingMovieAction) {

    }
}