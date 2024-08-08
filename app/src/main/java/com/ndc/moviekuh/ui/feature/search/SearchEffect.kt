package com.ndc.moviekuh.ui.feature.search

sealed interface SearchEffect {
    data object Empty : SearchEffect
}