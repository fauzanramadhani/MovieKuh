package com.ndc.moviekuh.ui.feature.detailmovie

import com.ndc.moviekuh.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(

) : BaseViewModel<DetailMovieState, DetailMovieAction, DetailMovieEffect>(
    DetailMovieState()
) {
    override fun onAction(action: DetailMovieAction) {

    }
}