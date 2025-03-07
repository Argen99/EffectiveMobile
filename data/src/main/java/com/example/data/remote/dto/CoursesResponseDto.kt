package com.example.data.remote.dto

data class CoursesResponseDto(
    val meta: MetaDto,
    val courses: List<CourseDto>,
)