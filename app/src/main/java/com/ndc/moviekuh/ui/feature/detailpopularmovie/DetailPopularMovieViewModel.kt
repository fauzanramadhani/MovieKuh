package com.ndc.moviekuh.ui.feature.detailpopularmovie

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ndc.moviekuh.base.BaseViewModel
import com.ndc.moviekuh.data.source.network.response.PopularMovieItem
import com.ndc.moviekuh.domain.GetPopularMoviePagingUseCase
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
class DetailPopularMovieViewModel @Inject constructor(
    private val getPopularMoviePagingUseCase: GetPopularMoviePagingUseCase
) : BaseViewModel<DetailPopularMovieState, DetailPopularMovieAction, DetailPopularMovieEffect>(
    DetailPopularMovieState()
) {
    private val _popularMoviePagingState: MutableStateFlow<PagingData<PopularMovieItem>> =
        MutableStateFlow(value = PagingData.empty())
    val popularMoviePagingState: StateFlow<PagingData<PopularMovieItem>> get() = _popularMoviePagingState

    init {
        getPopularMoviePaging()
    }

    override fun onAction(action: DetailPopularMovieAction) {

    }

    private fun getPopularMoviePaging() = viewModelScope.launch {
        getPopularMoviePagingUseCase
            .invoke()
            .distinctUntilChanged()
            .catch {
                onShowToast(it.message.toString())
            }
            .cachedIn(this)
            .onEach {
                _popularMoviePagingState.value = it
            }
            .collect()
    }

    private fun onShowToast(message: String) = viewModelScope.launch {
        sendEffect(DetailPopularMovieEffect.OnShowToast(message))
        delay(1000)
        sendEffect(DetailPopularMovieEffect.Empty)
    }
}