package com.ndc.moviekuh.ui.feature.detailtoprated

import androidx.lifecycle.viewModelScope
import com.ndc.moviekuh.base.BaseViewModel
import com.ndc.moviekuh.data.source.network.response.TopRatedMovieItem
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
class DetailTopRatedMovieViewModel @Inject constructor(
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase
) : BaseViewModel<DetailTopRatedMovieState, DetailTopRatedMovieAction, DetailTopRatedMovieEffect>(
    DetailTopRatedMovieState()
) {
    init {
        getTopRatedMovieList()
    }

    override fun onAction(action: DetailTopRatedMovieAction) {

    }

    private fun getTopRatedMovieList() = viewModelScope.launch {
        getTopRatedMovieListUseCase
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
                        topRatedMovieList = it as List<TopRatedMovieItem>,
                        topRatedMovieLoading = false
                    )
                }
            }
            .collect()
    }

    private fun onShowToast(message: String) = viewModelScope.launch {
        sendEffect(DetailTopRatedMovieEffect.OnShowToast(message))
        delay(1000)
        sendEffect(DetailTopRatedMovieEffect.Empty)
    }
}