package com.example.data.remote.repository

import com.example.core.Either
import com.example.core.model.Course
import com.example.data.core.utils.makeNetworkRequest
import com.example.data.core.utils.makeRequest
import com.example.data.local.room.dao.CourseDao
import com.example.data.local.room.entity.toEntity
import com.example.data.remote.api_service.MainApiService
import com.example.domain.model.Tag
import com.example.domain.model.User
import com.example.domain.repository.FavoritesRepository
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainRepositoryImpl(
    private val apiService: MainApiService,
    private val courseDao: CourseDao,
) : MainRepository, FavoritesRepository {

    override fun getCourses(
        tagId: Int?,
        isPaid: Boolean?,
    ): Flow<Either<String, List<Course>>> = makeNetworkRequest {
        apiService.getCourses(
            tagId = tagId,
            isPaid = isPaid,
        ).courses.map {
            it.toDomain().copy(isFavorite = courseDao.isCourseExists(it.id) > 0)
        }
    }

    override fun getCourseById(courseId: Int): Flow<Either<String, Course>> = makeNetworkRequest {
        apiService.getCourseById(courseId).courses.first().toDomain().copy(
            isFavorite = courseDao.isCourseExists(courseId) > 0
        )
    }

    override fun getUser(userId: Int): Flow<Either<String, User>> = makeNetworkRequest {
        apiService.getUser(userId).users.first().toDomain()
    }

    override fun getTags(): Flow<Either<String, List<Tag>>> = makeNetworkRequest {
        apiService.getTags().tags.map { it.toDomain() }
    }

    override fun getFavorites(): Flow<Either<String, List<Course>>> = makeRequest {
        courseDao.getCourses().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun onFavoriteClick(course: Course) {
        if (course.isFavorite) {
            courseDao.insert(course.toEntity())
        } else {
            courseDao.delete(course.toEntity())
        }
    }
}