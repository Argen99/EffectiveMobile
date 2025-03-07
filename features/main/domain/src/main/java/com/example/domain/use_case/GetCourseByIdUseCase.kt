package com.example.domain.use_case

import com.example.domain.repository.MainRepository

class GetCourseByIdUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(courseId: Int) = repository.getCourseById(courseId)
}