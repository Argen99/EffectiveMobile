package com.example.domain.use_case

import com.example.domain.repository.MainRepository

class GetCoursesUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(tagId: Int?, isPaid: Boolean?) =
        repository.getCourses(tagId, isPaid)
}