package com.ndc.moviekuh.ui.feature.search

import com.ndc.moviekuh.base.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(

) : BaseViewModel<SearchState, SearchAction, SearchEffect>(
    SearchState()
) {
    override fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchValueChange -> updateState { copy(searchValue = action.value) }
        }
    }
}