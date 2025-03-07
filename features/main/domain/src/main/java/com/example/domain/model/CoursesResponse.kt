package com.example.domain.model

import com.example.core.model.Course

data class CoursesResponse(
    val meta: Meta,
    val courses: List<Course>,
)