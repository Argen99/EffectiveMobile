package com.example.domain.use_case

import com.example.domain.repository.MainRepository

class GetTagsUseCase(
    private val repository: MainRepository
) {
    operator fun invoke() = repository.getTags()
}