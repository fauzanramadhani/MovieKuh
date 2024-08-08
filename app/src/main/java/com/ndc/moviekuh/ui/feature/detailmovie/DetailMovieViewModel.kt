package com.ndc.moviekuh.ui.feature.detailmovie

import androidx.lifecycle.viewModelScope
import com.ndc.moviekuh.base.BaseViewModel
import com.ndc.moviekuh.data.source.local.room.dto.FavoriteDto
import com.ndc.moviekuh.domain.AddToFavoriteUseCase
import com.ndc.moviekuh.domain.DeleteFavoriteByIdUseCase
import com.ndc.moviekuh.domain.IsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFavoriteByIdUseCase: DeleteFavoriteByIdUseCase,
) : BaseViewModel<DetailMovieState, DetailMovieAction, DetailMovieEffect>(
    DetailMovieState()
) {
    override fun onAction(action: DetailMovieAction) {
        when (action) {
            is DetailMovieAction.OnFavoriteChange -> onFavoriteChange(action.favoriteDto)
            is DetailMovieAction.OnGetIsFavorite -> getIsFavorite(action.id)
        }
    }

    private fun getIsFavorite(
        id: Int
    ) = viewModelScope.launch {
        try {
            val isFavorite = isFavoriteUseCase.invoke(id)
            updateState { copy(isFavorite = isFavorite) }
        } catch (e: Exception) {
            onShowToast(e.message.toString())
        }

    }

    private fun onFavoriteChange(
        favoriteDto: FavoriteDto
    ) = viewModelScope.launch {
        try {
            val currentFavoriteValue = state.value.isFavorite
            if (currentFavoriteValue) {
                deleteFavoriteByIdUseCase.invoke(favoriteDto.id)
                updateState { copy(isFavorite = false) }
            } else {
                addToFavoriteUseCase.invoke(favoriteDto)
                updateState { copy(isFavorite = true) }
            }
        } catch (e: Exception) {
            onShowToast(e.message.toString())
        }
    }

    private fun onShowToast(message: String) = viewModelScope.launch {
        sendEffect(DetailMovieEffect.OnShowToast(message))
        delay(1000)
        sendEffect(DetailMovieEffect.Empty)
    }
}