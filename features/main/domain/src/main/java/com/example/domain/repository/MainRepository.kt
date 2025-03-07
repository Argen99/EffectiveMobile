package com.example.domain.repository

import com.example.core.Either
import com.example.core.model.Course
import com.example.domain.model.Tag
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getCourses(tagId: Int?, isPaid: Boolean?): Flow<Either<String, List<Course>>>
    fun getCourseById(courseId: Int): Flow<Either<String, Course>>
    fun getUser(userId: Int): Flow<Either<String, User>>
    fun getTags(): Flow<Either<String, List<Tag>>>
    suspend fun onFavoriteClick(course: Course)
}