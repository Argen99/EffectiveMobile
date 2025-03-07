package com.example.domain.use_case

import com.example.core.model.Course
import com.example.domain.repository.MainRepository

class OnFavoritesClickUseCase(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(course: Course) = mainRepository.onFavoriteClick(course)
}