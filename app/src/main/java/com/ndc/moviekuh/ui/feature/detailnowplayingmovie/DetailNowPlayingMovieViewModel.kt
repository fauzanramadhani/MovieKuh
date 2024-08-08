package com.ndc.moviekuh.ui.feature.detailnowplayingmovie

import androidx.lifecycle.viewModelScope
import com.ndc.moviekuh.base.BaseViewModel
import com.ndc.moviekuh.data.source.network.response.NowPlayingMovieItem
import com.ndc.moviekuh.domain.GetNowPlayingMovieListUseCase
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
class DetailNowPlayingMovieViewModel @Inject constructor(
    private val getNowPlayingMovieListUseCase: GetNowPlayingMovieListUseCase
) : BaseViewModel<DetailNowPlayingMovieState, DetailNowPlayingMovieAction, DetailNowPlayingMovieEffect>(
    DetailNowPlayingMovieState()
) {
    init {
        getNowPlayingMovieList()
    }

    private fun getNowPlayingMovieList() = viewModelScope.launch {
        getNowPlayingMovieListUseCase
            .invoke()
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

    private fun onShowToast(message: String) = viewModelScope.launch {
        sendEffect(DetailNowPlayingMovieEffect.OnShowToast(message))
        delay(1000)
        sendEffect(DetailNowPlayingMovieEffect.Empty)
    }

    override fun onAction(action: DetailNowPlayingMovieAction) {

    }
}