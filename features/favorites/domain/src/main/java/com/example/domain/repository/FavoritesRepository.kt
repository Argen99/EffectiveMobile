package com.example.domain.repository

import com.example.core.Either
import com.example.core.model.Course
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<Either<String, List<Course>>>
    suspend fun onFavoriteClick(course: Course)
}