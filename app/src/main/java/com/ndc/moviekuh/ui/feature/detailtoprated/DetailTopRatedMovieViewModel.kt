package com.ndc.moviekuh.ui.feature.detailtoprated

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ndc.moviekuh.base.BaseViewModel
import com.ndc.moviekuh.data.source.network.response.TopRatedMovieItem
import com.ndc.moviekuh.domain.GetTopRatedMoviePagingUseCase
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
class DetailTopRatedMovieViewModel @Inject constructor(
    private val getTopRatedMoviePagingUseCase: GetTopRatedMoviePagingUseCase
) : BaseViewModel<DetailTopRatedMovieState, DetailTopRatedMovieAction, DetailTopRatedMovieEffect>(
    DetailTopRatedMovieState()
) {
    private val _topRatedMoviePagingState: MutableStateFlow<PagingData<TopRatedMovieItem>> =
        MutableStateFlow(value = PagingData.empty())
    val topRatedMoviePagingState: StateFlow<PagingData<TopRatedMovieItem>> get() = _topRatedMoviePagingState

    init {
        getTopRatedMoviePaging()
    }

    override fun onAction(action: DetailTopRatedMovieAction) {

    }

    private fun getTopRatedMoviePaging() = viewModelScope.launch {
        getTopRatedMoviePagingUseCase
            .invoke()
            .distinctUntilChanged()
            .catch {
                onShowToast(it.message.toString())
            }
            .cachedIn(this)
            .onEach {
                _topRatedMoviePagingState.value = it
            }
            .collect()
    }

    private fun onShowToast(message: String) = viewModelScope.launch {
        sendEffect(DetailTopRatedMovieEffect.OnShowToast(message))
        delay(1000)
        sendEffect(DetailTopRatedMovieEffect.Empty)
    }
}