package com.ndc.moviekuh.ui.feature.search

sealed interface SearchAction {
    data class OnSearchValueChange(
        val value: String
    ) : SearchAction
}