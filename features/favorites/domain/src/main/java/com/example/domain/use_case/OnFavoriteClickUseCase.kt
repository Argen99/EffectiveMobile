package com.example.domain.use_case

import com.example.core.model.Course
import com.example.domain.repository.FavoritesRepository

class OnFavoriteClickUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(course: Course) = repository.onFavoriteClick(course)
}