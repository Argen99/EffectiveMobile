package com.example.presentation.ui.fragment

import androidx.lifecycle.viewModelScope
import com.example.core.model.Course
import com.example.core_ui.base.BaseViewModel
import com.example.domain.use_case.GetFavoritesUseCase
import com.example.domain.use_case.OnFavoriteClickUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val onFavoriteClickUseCase: OnFavoriteClickUseCase
): BaseViewModel() {

    private val _favoritesState = mutableUiStateFlow<List<Course>>()
    val favoritesState = _favoritesState.asStateFlow()

    init {
        getFavoritesUseCase().gatherRequest(_favoritesState)
    }

    fun onFavoriteClick(course: Course) {
        viewModelScope.launch {
            onFavoriteClickUseCase(course)
        }
    }
}