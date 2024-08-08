package com.ndc.moviekuh.ui.feature.detailpopularmovie

import androidx.lifecycle.viewModelScope
import com.ndc.moviekuh.base.BaseViewModel
import com.ndc.moviekuh.data.source.network.response.PopularMovieItem
import com.ndc.moviekuh.domain.GetPopularMovieListUseCase
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
class DetailPopularMovieViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase
) : BaseViewModel<DetailPopularMovieState, DetailPopularMovieAction, DetailPopularMovieEffect>(
    DetailPopularMovieState()
) {
    init {
        getPopularMovieList()
    }

    override fun onAction(action: DetailPopularMovieAction) {

    }

    private fun getPopularMovieList() = viewModelScope.launch {
        getPopularMovieListUseCase
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
                        popularMovieList = it as List<PopularMovieItem>,
                        popularMovieLoading = false
                    )
                }
            }
            .collect()
    }

    private fun onShowToast(message: String) = viewModelScope.launch {
        sendEffect(DetailPopularMovieEffect.OnShowToast(message))
        delay(1000)
        sendEffect(DetailPopularMovieEffect.Empty)
    }
}