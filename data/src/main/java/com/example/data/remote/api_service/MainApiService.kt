package com.example.data.remote.api_service

import com.example.data.remote.dto.CoursesResponseDto
import com.example.data.remote.dto.TagsResponseDto
import com.example.data.remote.dto.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApiService {

    @GET("/api/courses")
    suspend fun getCourses(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("page_size") pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("tag") tagId: Int?,
        @Query("is_paid") isPaid: Boolean?,
        @Query("is_popular") isPopular: Boolean = IS_POPULAR,
    ): CoursesResponseDto

    @GET("/api/courses/{pk}")
    suspend fun getCourseById(
        @Path("pk") courseId: Int
    ): CoursesResponseDto

    @GET("/api/users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: Int
    ): UserResponseDto

    @GET("/api/tags")
    suspend fun getTags(): TagsResponseDto

    companion object {
        const val IS_POPULAR = true
        const val DEFAULT_PAGE = 1
        const val DEFAULT_PAGE_SIZE = 40
    }
}