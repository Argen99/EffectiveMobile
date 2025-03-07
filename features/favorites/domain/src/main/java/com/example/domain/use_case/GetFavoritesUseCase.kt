package com.example.domain.use_case

import com.example.domain.repository.FavoritesRepository

class GetFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke() = favoritesRepository.getFavorites()
}